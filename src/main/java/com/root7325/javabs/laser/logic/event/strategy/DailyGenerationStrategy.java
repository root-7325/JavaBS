package com.root7325.javabs.laser.logic.event.strategy;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of event generation strategy for {@link EventSlotType#Daily} slot type.
 *
 * @author root7325 on 02.07.2025
 * @see IEventGenerationStrategy
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class DailyGenerationStrategy implements IEventGenerationStrategy {
    private final List<GameMode> modes = List.of(GameMode.LaserBall, GameMode.AttackDefend);
    private final LocationManager locationManager;

    @Override
    public boolean supports(EventSlotType type) {
        return type == EventSlotType.Daily;
    }

    @Override
    public GameMode nextMode(GameMode currentMode) {
        if (currentMode == null) {
            return modes.get(0);
        }

        int idx = modes.indexOf(currentMode);
        int nextIdx = (idx + 1) % modes.size();
        return modes.get(nextIdx);
    }

    @Override
    public Event generate(EventSlotType type, GameMode requiredMode, int pairMapId) {
        Objects.requireNonNull(requiredMode, "requiredMode must be specified for Daily event");

        return locationManager.getRandomByGameMode(requiredMode)
                .map(location -> new Event(type.getIndex(), location.getId(), Instant.now().plus(requiredMode.getDuration())))
                .orElseThrow(() -> new IllegalStateException("No location found for Daily event"));
    }
}
