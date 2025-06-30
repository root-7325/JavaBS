package com.root7325.javabs.laser.logic.event;

import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.Instant;

/**
 * @author root7325 on 26.06.2025
 */
@AllArgsConstructor
public class Event {
    private final int index;
    private final boolean next;
    private final int mapId;
    private final Instant instant;

    private int getRemainingTime() {
        Duration duration = Duration.between(Instant.now(), instant);

        if (duration.isNegative()) return -1;

        return (int) duration.getSeconds();
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(0);
        out.writeVInt(index);
        out.writeVInt(next ? getRemainingTime() : -1);
        out.writeVInt(getRemainingTime());
        out.writeVInt(0);
        out.writeDataReference(15, mapId);
        out.writeVInt(3);
        out.writeString();
        out.writeVInt(0);
        out.writeVInt(0);
    }
}
