package com.root7325.javabs.laser.logic.event.strategy;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Optional;

/**
 * Implementation of event generation strategy for {@link EventSlotType#BattleRoyaleTeam} slot type.
 *
 * @author root7325 on 02.07.2025
 * @see IEventGenerationStrategy
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class BattleRoyaleTeamGenerationStrategy implements IEventGenerationStrategy {
    private final LocationManager locationManager;

    @Override
    public boolean supports(EventSlotType type) {
        return type == EventSlotType.BattleRoyaleTeam;
    }

    @Override
    public GameMode nextMode(GameMode currentMode) {
        return GameMode.BattleRoyaleTeam;
    }

    @Override
    public Event generate(EventSlotType type, GameMode requiredMode, int pairMapId) {
        if (pairMapId == -1) {
            throw new IllegalArgumentException("pairMapId should be specified for BattleRoyaleTeam event");
        }

        return locationManager.getTeamPairById(pairMapId)
                .map(location -> new Event(
                        type.getIndex(),
                        location.getId(),
                        Instant.now().plus(GameMode.BattleRoyaleTeam.getDuration())
                ))
                .orElseThrow(() -> new IllegalStateException("No location found for BattleRoyaleTeam"));
    }
}
