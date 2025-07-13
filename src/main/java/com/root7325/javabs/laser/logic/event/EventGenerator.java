package com.root7325.javabs.laser.logic.event;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.strategy.IEventGenerationStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This class manages generation of game events and mode rotations by delegating
 * to appropriate strategy implementations based on event slot types.
 *
 * @author root7325 on 02.07.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class EventGenerator {
    private final Set<IEventGenerationStrategy> strategies;

    /**
     * Determines next game mode in rotation sequence for given event slot type.
     *
     * @param type event slot type to determine strategy
     * @param currentMode current game mode to rotate from
     * @return next game mode in rotation sequence
     * @throws IllegalArgumentException if no strategy supports given event slot type
     */
    public GameMode generateNextMode(EventSlotType type, GameMode currentMode) {
        return strategies.stream()
                .filter(s -> s.supports(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No strategy for " + type))
                .nextMode(currentMode);
    }

    /**
     * Generates a new event.
     *
     * @param type event slot type for event generation
     * @param requiredMode specific game mode required for event
     * @param pairMapId map ID for pairing BattleRoyale modes
     * @return generated event instance
     * @throws IllegalArgumentException if no strategy supports the given event slot type
     */
    public Event generateNextEvent(EventSlotType type, GameMode requiredMode, int pairMapId) {
        return strategies.stream()
            .filter(s -> s.supports(type))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No strategy for " + type))
            .generate(type, requiredMode, pairMapId);
    }

    public Event generateNextEvent(EventSlotType type) {
        return generateNextEvent(type, null, -1);
    }

    public Event generateNextEvent(EventSlotType type, GameMode requiredMode) {
        return generateNextEvent(type, requiredMode, -1);
    }

    public Event generateNextEvent(EventSlotType type, int pairMapId) {
        return generateNextEvent(type, null, pairMapId);
    }
}
