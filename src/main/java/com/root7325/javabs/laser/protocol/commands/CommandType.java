package com.root7325.javabs.laser.protocol.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of command types in SC (Laser) protocol.
 * @author root7325 on 28.06.2025
 */
@Getter
@AllArgsConstructor
public enum CommandType {
    Gatcha(500),
    SetPlayerThumbnail(505),
    PurchaseDoubleCoinsCommand(509),
    PurchaseOffer(519),

    ChangeAvatarName(201),
    DiamondsAdded(202),
    GiveDeliveryItems(203),
    DayChanged(204),
    PurchaseHeroLvlUpMaterial(521);

    private final int i;

    /**
     * Converts command type ID to corresponding CommandType enum.
     *
     * @param type command type ID
     * @return corresponding CommandType enum value
     * @throws IllegalArgumentException if no CommandType exists with the given ID
     */
    public static CommandType from(int type) {
        for (CommandType commandType : values()) {
            if (commandType.i == type) {
                return commandType;
            }
        }
        throw new IllegalArgumentException("No CommandType with type " + type);
    }
}
