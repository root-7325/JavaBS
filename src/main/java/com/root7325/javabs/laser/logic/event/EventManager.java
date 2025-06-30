package com.root7325.javabs.laser.logic.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author root7325 on 26.06.2025
 */
@Slf4j
@Singleton
public class EventManager {
    private final LocationManager locationManager;
    private final long slotLifetime = TimeUnit.SECONDS.toSeconds(2);
    private final List<EventSlot> eventSlots;

    @Inject
    public EventManager(LocationManager locationManager) {
        this.locationManager = locationManager;
        this.eventSlots = new ArrayList<>();

        eventSlots.add(generateEventSlot(EventSlotType.CoinRush, GameMode.CoinRush));
        eventSlots.add(generateEventSlot(EventSlotType.BattleRoyale, GameMode.BattleRoyale));
        eventSlots.add(generateEventSlot(EventSlotType.Daily, GameMode.LaserBall, GameMode.AttackDefend));
        eventSlots.add(generateEventSlot(EventSlotType.Special, GameMode.BountyHunter));
    }

    private Event generateEvent(EventSlotType slotType, boolean next, GameMode mode) {
        int mapId = locationManager.getRandomByGameMode(mode)
                .map(Location::getId)
                .orElseThrow(() -> new IllegalArgumentException("Failed to generate mapId for " + mode.toString()));
        return new Event(slotType.getIndex(), next, mapId, Instant.now().plusSeconds(slotLifetime));
    }

    private EventSlot generateEventSlot(EventSlotType eventSlotType, GameMode... gameModes) {
        int slotIndex = getNextSlotIndex();
        EventSlot eventSlot = new EventSlot(eventSlotType, gameModes, slotIndex);
        Event event = generateEvent(eventSlotType, false, eventSlot.getCurrentMode());
        Event nextEvent = generateEvent(eventSlotType, true, eventSlot.getNextMode());
        eventSlot.setEvent(event);
        eventSlot.setNextEvent(nextEvent);
        eventSlots.add(eventSlot);
        return eventSlot;
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(eventSlots.size());
        for (EventSlot slot : eventSlots) {
            slot.getEvent().encode(out);
        }

        out.writeVInt(eventSlots.size());
        for (EventSlot slot : eventSlots) {
            slot.getNextEvent().encode(out);
        }
    }

    public int getNextSlotIndex() {
        return eventSlots.size() + 1;
    }

    public List<EventSlot> getEventSlots() {
        return Collections.unmodifiableList(this.eventSlots);
    }
}