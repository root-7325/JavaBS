package com.root7325.javabs.laser.logic.event;

import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author root7325 on 30.06.2025
 */
@RequiredArgsConstructor
public class EventSlot {
    private final EventSlotType eventSlotType;
    private final GameMode[] allowedModes;
    private int modeIndex;
    @Getter
    private final int eventIndex;

    @Setter
    @Getter
    private Event event;
    @Setter
    @Getter
    private Event nextEvent;

    public GameMode getCurrentMode() {
        return allowedModes[modeIndex % allowedModes.length];
    }

    public GameMode getNextMode() {
        return allowedModes[(modeIndex + 1) % allowedModes.length];
    }
}
