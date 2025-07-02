package com.root7325.javabs.laser.logic.event.strategy;

/**
 * @author root7325 on 02.07.2025
 */
import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;

public interface IEventGenerationStrategy {
    boolean supports(EventSlotType type);
    GameMode nextMode(GameMode currentMode);
    Event generate(EventSlotType type, GameMode requiredMode, int pairMapId);
}
