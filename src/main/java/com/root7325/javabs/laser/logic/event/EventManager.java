package com.root7325.javabs.laser.logic.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.core.IPacketDispatcher;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.protocol.commands.server.LogicDayChangedCommand;
import com.root7325.javabs.laser.protocol.packets.ILaserServerMessageFactory;
import com.root7325.javabs.laser.protocol.packets.server.AvailableServerCommandMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author root7325 on 26.06.2025
 */
@Slf4j
@Singleton
public class EventManager {
    public static final long SLOT_LIFETIME = TimeUnit.MINUTES.toSeconds(30);
    private final ILaserServerMessageFactory laserServerMessageFactory;
    private final IPacketDispatcher packetDispatcher;
    private final EventGenerator eventGenerator;
    private final ScheduledExecutorService scheduler;
    private final List<EventSlot> eventSlots;

    @Inject
    public EventManager(ILaserServerMessageFactory laserServerMessageFactory, IPacketDispatcher packetDispatcher,
                        EventGenerator eventGenerator, ScheduledExecutorService scheduler) {
        this.laserServerMessageFactory = laserServerMessageFactory;
        this.packetDispatcher = packetDispatcher;
        this.eventGenerator = eventGenerator;
        this.scheduler = scheduler;
        this.eventSlots = new CopyOnWriteArrayList<>();
        initializeSlots();
        scheduler.scheduleAtFixedRate(this::updateEvents, SLOT_LIFETIME, SLOT_LIFETIME, TimeUnit.SECONDS);
    }

    private void initializeSlots() {
        eventSlots.clear();
        Instant initialInstant = Instant.now().plusSeconds(SLOT_LIFETIME);

        EventSlot coinRushSlot = createSlot(EventSlotType.CoinRush, initialInstant);
        EventSlot battleRoyaleSlot = createSlot(EventSlotType.BattleRoyale, initialInstant);
        EventSlot dailySlot = createDailySlot(initialInstant);
        EventSlot battleRoyaleTeamSlot = createBattleRoyaleTeamSlot(initialInstant, battleRoyaleSlot.getEvent().getMapId());

        eventSlots.addAll(List.of(coinRushSlot, battleRoyaleSlot, dailySlot, battleRoyaleTeamSlot));
    }

    private EventSlot createSlot(EventSlotType type, Instant instant) {
        EventSlot slot = new EventSlot(type);
        Event event = eventGenerator.generateNextEvent(type);
        event.setInstant(instant);
        slot.setEvent(event);
        return slot;
    }

    private EventSlot createDailySlot(Instant instant) {
        EventSlot slot = new EventSlot(EventSlotType.Daily);
        slot.setCurrentMode(GameMode.LaserBall);
        Event event = eventGenerator.generateNextEvent(EventSlotType.Daily, GameMode.LaserBall);
        event.setInstant(instant);
        slot.setEvent(event);
        return slot;
    }

    private EventSlot createBattleRoyaleTeamSlot(Instant instant, int mapId) {
        EventSlot slot = new EventSlot(EventSlotType.BattleRoyaleTeam);
        Event event = eventGenerator.generateNextEvent(EventSlotType.BattleRoyaleTeam, mapId);
        event.setInstant(instant);
        slot.setEvent(event);
        return slot;
    }

    private void updateEvents() {
        Instant updateInstant = Instant.now().plusSeconds(SLOT_LIFETIME);
        int battleRoyaleMapId = -1;

        for (EventSlot eventSlot : eventSlots) {
            switch (eventSlot.getSlotType()) {
                case CoinRush, BattleRoyale -> {
                    Event event = eventGenerator.generateNextEvent(eventSlot.getSlotType());
                    eventSlot.setEvent(event);

                    if (eventSlot.getSlotType() == EventSlotType.BattleRoyale) {
                        battleRoyaleMapId = event.getMapId();
                    }
                }
                case BattleRoyaleTeam -> {
                    if (battleRoyaleMapId == -1) {
                        log.warn("BattleRoyale mapId is not assigned.");
                        continue;
                    }

                    Event event = eventGenerator.generateNextEvent(EventSlotType.BattleRoyaleTeam, battleRoyaleMapId);
                    eventSlot.setEvent(event);
                }
                case Daily -> {
                    GameMode mode = eventGenerator.generateNextMode(EventSlotType.Daily, eventSlot.getCurrentMode());
                    eventSlot.setCurrentMode(mode);

                    Event event = eventGenerator.generateNextEvent(EventSlotType.Daily, mode);
                    eventSlot.setEvent(event);
                }
            }

            eventSlot.getEvent().setInstant(updateInstant);
        }

        packetDispatcher.broadcast(player -> {
            OwnHomeDataMessage ownHomeDataMessage = laserServerMessageFactory.createOwnHomeDataMessage(player);

            LogicDayChangedCommand logicDayChangedCommand = new LogicDayChangedCommand(ownHomeDataMessage);
            return new AvailableServerCommandMessage(logicDayChangedCommand);
        });
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