package com.root7325.javabs.laser.logic.event.strategy;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author root7325 on 02.07.2025
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
        int idx = modes.indexOf(currentMode);
        int nextIdx = (idx + 1) % modes.size();
        return modes.get(nextIdx);
    }

    @Override
    public Event generate(EventSlotType type, GameMode requiredMode, int pairMapId) {
        if (requiredMode == null) {
            throw new IllegalArgumentException("requiredMode must be specified for Daily event");
        }
        Optional<Location> locationOptional = locationManager.getRandomByGameMode(requiredMode);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            return new Event(type.getIndex(), location.getId());
        }
        throw new IllegalStateException("No location found for Daily event");
    }
}
