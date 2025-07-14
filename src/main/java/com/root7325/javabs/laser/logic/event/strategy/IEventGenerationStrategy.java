package com.root7325.javabs.laser.logic.event.strategy;

import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import com.root7325.javabs.laser.logic.event.Event;

/**
 * Strategy interface for generating game events based on different event slot types.
 *
 * @author root7325 on 02.07.2025
 */
public interface IEventGenerationStrategy {
    /**
     * Validates that given event slot type is supported by this strategy.
     *
     * @param type event slot type to check
     * @return {@code true} if supported, {@code false} otherwise
     */
    boolean supports(EventSlotType type);

    /**
     * Performs game mode rotation to determine next mode in sequence.
     *
     * @param currentMode current game mode
     * @return next game mode in rotation
     */
    GameMode nextMode(GameMode currentMode);

    /**
     * Performs new event generation. Some of params may be null, depending
     * on specific strategy implementation.
     *
     * @param type event slot type
     * @param requiredMode required mode for generation
     * @param pairMapId used only for pairing {@link GameMode#BattleRoyale} and {@link GameMode#BattleRoyaleTeam} modes
     * @return generated event
     */
    Event generate(EventSlotType type, GameMode requiredMode, int pairMapId);
}
