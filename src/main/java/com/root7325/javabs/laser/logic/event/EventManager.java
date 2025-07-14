package com.root7325.javabs.laser.logic.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.root7325.javabs.laser.core.IPacketDispatcher;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.protocol.commands.server.LogicDayChangedCommand;
import com.root7325.javabs.laser.protocol.packets.ILaserServerMessageFactory;
import com.root7325.javabs.laser.protocol.packets.server.AvailableServerCommandMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class manages lifecycle and scheduling of events.
 *
 * @author root7325 on 26.06.2025
 */
@Slf4j
public class EventManager {
    public static final Duration SLOT_POLL_INTERVAL = Duration.ofSeconds(30); // 1/2 minute
    private final ILaserServerMessageFactory laserServerMessageFactory;
    private final IPacketDispatcher packetDispatcher;
    private final EventGenerator eventGenerator;
    private final List<EventSlot> eventSlots;

    @Inject
    public EventManager(ILaserServerMessageFactory laserServerMessageFactory, IPacketDispatcher packetDispatcher,
                        EventGenerator eventGenerator, ScheduledExecutorService scheduler) {
        this.laserServerMessageFactory = laserServerMessageFactory;
        this.packetDispatcher = packetDispatcher;
        this.eventGenerator = eventGenerator;
        this.eventSlots = new CopyOnWriteArrayList<>();
        initializeSlots();
        scheduler.scheduleAtFixedRate(this::updateEvents, SLOT_POLL_INTERVAL.getSeconds(), SLOT_POLL_INTERVAL.getSeconds(), TimeUnit.SECONDS);
    }

    /** Initializes all event slots. */
    private void initializeSlots() {
        log.debug("Initializing event slots...");
        eventSlots.clear();

        try {
            EventSlot coinRushSlot = createSlot(EventSlotType.CoinRush);
            EventSlot battleRoyaleSlot = createSlot(EventSlotType.BattleRoyale);
            EventSlot dailySlot = createSlot(EventSlotType.Daily);
            EventSlot battleRoyaleTeamSlot = createBattleRoyaleTeamSlot(battleRoyaleSlot.getEvent().getInstant(), battleRoyaleSlot.getEvent().getMapId());
            EventSlot specialSlot = createSlot(EventSlotType.Special);

            eventSlots.addAll(List.of(coinRushSlot, battleRoyaleSlot, dailySlot, battleRoyaleTeamSlot, specialSlot));
            log.debug("Total of {} event slots initialized successfully!", eventSlots.size());
        } catch (Exception ex) {
            log.error("Failed to initialize slots.", ex);
        }
    }

    /**
     * Creates a new event slot.
     *
     * @param type type of this event slot
     * @return created event slot with appropriate game mode, event data and instant
     */
    private EventSlot createSlot(EventSlotType type) {
        EventSlot slot = new EventSlot(type);
        GameMode mode = eventGenerator.generateNextMode(type, null);
        Event event = eventGenerator.generateNextEvent(type, mode);
        event.setInstant(Instant.now().plus(mode.getDuration()));
        slot.setEvent(event);
        return slot;
    }

    /**
     * Creates a new event slot for BattleRoyaleTeam
     *
     * @param battleRoyaleInstant instant from slot with BattleRoyale type
     * @param mapId id of map from slot with BattleRoyale type
     * @return created BattleRoyaleTeam event slot
     */
    private EventSlot createBattleRoyaleTeamSlot(Instant battleRoyaleInstant, int mapId) {
        EventSlot slot = new EventSlot(EventSlotType.BattleRoyaleTeam);
        Event event = eventGenerator.generateNextEvent(EventSlotType.BattleRoyaleTeam, mapId);
        event.setInstant(battleRoyaleInstant);
        slot.setEvent(event);
        return slot;
    }

    /** Updates all events. */
    private void updateEvents() {
        int battleRoyaleMapId = -1;
        Instant battleRoyaleInstant = Instant.MIN;
        boolean broadcastRequired = false;

        try {
            for (EventSlot eventSlot : eventSlots) {
                if (!eventSlot.isEventExpired()) {
                    continue;
                }
                broadcastRequired = true;

                switch (eventSlot.getSlotType()) {
                    case CoinRush, BattleRoyale -> {
                        Event event = eventGenerator.generateNextEvent(eventSlot.getSlotType());
                        eventSlot.setEvent(event);

                        if (eventSlot.getSlotType() == EventSlotType.BattleRoyale) {
                            battleRoyaleMapId = event.getMapId();
                            battleRoyaleInstant = event.getInstant();
                        }
                    }
                    case BattleRoyaleTeam -> {
                        if (battleRoyaleMapId == -1) {
                            log.warn("BattleRoyale mapId is not assigned.");
                            continue;
                        }
                        if (battleRoyaleInstant == Instant.MIN) {
                            log.warn("BattleRoyale instant is not assigned.");
                            continue;
                        }

                        Event event = eventGenerator.generateNextEvent(EventSlotType.BattleRoyaleTeam, battleRoyaleMapId);
                        event.setInstant(battleRoyaleInstant);
                        eventSlot.setEvent(event);
                    }
                    case Daily, Special -> {
                        GameMode mode = eventGenerator.generateNextMode(eventSlot.getSlotType(), eventSlot.getCurrentMode());
                        eventSlot.setCurrentMode(mode);

                        Event event = eventGenerator.generateNextEvent(eventSlot.getSlotType(), mode);
                        eventSlot.setEvent(event);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Failed to update events.", ex);
        }

        if (broadcastRequired) {
            packetDispatcher.broadcast(player -> {
                OwnHomeDataMessage ownHomeDataMessage = laserServerMessageFactory.createOwnHomeDataMessage(player);

                LogicDayChangedCommand logicDayChangedCommand = new LogicDayChangedCommand(ownHomeDataMessage);
                return new AvailableServerCommandMessage(logicDayChangedCommand);
            });
        }
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(eventSlots.size());
        for (EventSlot slot : eventSlots) {
            slot.getEvent().encode(out);
        }

        out.writeVInt(0);
    }

    public List<EventSlot> getEventSlots() {
        return Collections.unmodifiableList(this.eventSlots);
    }
}