package com.root7325.javabs.laser.logic.common;

import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author root7325 on 22.06.2025
 */
@Getter
@AllArgsConstructor
public class LogicDataSlot {
    private final GlobalId globalId;
    private int amount;

    public void encode(LaserByteBuf out) {
        out.writeDataReference(globalId);
        out.writeVInt(amount);
    }

    public void add(int i) {
        amount += i;
    }

    public boolean tryUse(int i) {
        if ((amount - i) >= 0) {
            amount -= i;
            return true;
        }
        return false;
    }
}