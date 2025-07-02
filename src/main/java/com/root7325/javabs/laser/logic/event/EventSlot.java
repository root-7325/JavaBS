package com.root7325.javabs.laser.logic.event;

import com.root7325.javabs.laser.enums.EventSlotType;
import com.root7325.javabs.laser.enums.GameMode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author root7325 on 30.06.2025
 */
@Getter
@Setter
@RequiredArgsConstructor
public class EventSlot {
    private final EventSlotType slotType;
    private GameMode currentMode;

    private Event event;
}
