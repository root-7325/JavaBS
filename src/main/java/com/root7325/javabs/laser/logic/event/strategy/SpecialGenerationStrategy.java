package com.root7325.javabs.laser.logic.event.strategy;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;
import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of event generation strategy for {@link EventSlotType#Special} slot type.
 *
 * @author root7325 on 13.07.2025
 * @see IEventGenerationStrategy
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class SpecialGenerationStrategy implements IEventGenerationStrategy {

    private final GameMode defaultMode = GameMode.BountyHunter;
    private final List<GameMode> specialModes = List.of(GameMode.BossFight, GameMode.Survival);

    private final LocationManager locationManager;

    @Override
    public boolean supports(EventSlotType type) {
        return type == EventSlotType.Special;
    }

    @Override
    public GameMode nextMode(GameMode currentMode) {
        LocalDate date = LocalDate.now();

        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            if (currentMode == null || currentMode == defaultMode) {
                return specialModes.get(0);
            }

            int idx = specialModes.indexOf(currentMode);
            int nextIdx = (idx + 1) % specialModes.size();
            return specialModes.get(nextIdx);
        }
        return defaultMode;
    }

    @Override
    public Event generate(EventSlotType type, GameMode requiredMode, int pairMapId) {
        Objects.requireNonNull(requiredMode, "requiredMode must be specified for Special event");

        return locationManager.getRandomByGameMode(requiredMode)
                .map(location -> new Event(type.getIndex(), location.getId(), Instant.now().plus(requiredMode.getDuration())))
                .orElseThrow(() -> new IllegalStateException("No location found for Special event"));
    }
}
