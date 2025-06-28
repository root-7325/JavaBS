package com.root7325.javabs.laser.logic.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author root7325 on 26.06.2025
 */
@Slf4j
@Singleton
public class EventManager {
    private static final List<String> GAME_MODES = List.of("CoinRush", "BattleRoyale", "LaserBall", "AttackDefend");
    private final List<Event> events;

    @Inject
    public EventManager(LocationManager locationManager) {
        this.events = new ArrayList<>();
        List<String> failedModes = new ArrayList<>();
        for (String mode : GAME_MODES) {
            if (!addEventForMode(locationManager, mode)) {
                failedModes.add(mode);
            }
        }
        if (!failedModes.isEmpty()) {
            log.warn("Failed to pick random for modes: {}!", failedModes);
        }
        if (events.isEmpty()) {
            throw new RuntimeException("Failed to add events.");
        }
    }

    private boolean addEventForMode(LocationManager locationManager, String gameMode) {
        return locationManager.getRandomByGameMode(gameMode)
                .map(location -> {
                    events.add(new Event(events.size() + 1, location.getId()));
                    return true;
                })
                .orElse(false);
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(this.events.size());
        for (Event event : this.events) {
            event.encode(out);
        }
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(this.events);
    }
}