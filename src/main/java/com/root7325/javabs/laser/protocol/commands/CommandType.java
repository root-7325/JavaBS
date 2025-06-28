package com.root7325.javabs.laser.protocol.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
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

    public static CommandType from(int type) {
        for (CommandType commandType : values()) {
            if (commandType.i == type) {
                return commandType;
            }
        }
        throw new IllegalArgumentException("No CommandType with type " + type);
    }
}
