package com.root7325.javabs.laser.enums;

/**
 * @author root7325 on 30.06.2025
 */
public enum EventSlotType {
    CoinRush,
    BattleRoyale,
    Daily,
    Special,
    BattleRoyaleTeam;

    public int getIndex() {
        return ordinal() + 1;
    }
}
