package com.root7325.javabs.laser.logic.event;

import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 26.06.2025
 */
@AllArgsConstructor
public class Event {
    private final int index;
    private final int mapId;

    public void encode(LaserByteBuf out) {
        out.writeVInt(0);
        out.writeVInt(index);
        out.writeVInt(index);
        out.writeVInt(5000);
        out.writeVInt(25);
        out.writeDataReference(15, mapId);
        out.writeVInt(3);
        out.writeString();
        out.writeVInt(0);
        out.writeVInt(0);
    }
}
