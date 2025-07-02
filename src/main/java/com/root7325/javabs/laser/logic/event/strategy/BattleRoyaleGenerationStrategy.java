package com.root7325.javabs.laser.logic.event.strategy;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;
import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * @author root7325 on 02.07.2025
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class BattleRoyaleGenerationStrategy implements IEventGenerationStrategy {
    private final LocationManager locationManager;

    @Override
    public boolean supports(EventSlotType type) {
        return type == EventSlotType.BattleRoyale;
    }

    @Override
    public GameMode nextMode(GameMode currentMode) {
        return GameMode.BattleRoyale;
    }

    @Override
    public Event generate(EventSlotType type, GameMode requiredMode, int pairMapId) {
        Optional<Location> locationOptional = locationManager.getRandomByGameMode(GameMode.BattleRoyale);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            return new Event(type.getIndex(), location.getId());
        }
        throw new IllegalStateException("No location found for BattleRoyale");
    }
}
