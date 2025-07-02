package com.root7325.javabs.laser.logic.event;

import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

/**
 * @author root7325 on 26.06.2025
 */
@RequiredArgsConstructor
public class Event {
    private final int index;
    @Getter
    private final int mapId;
    @Setter
    private Instant instant;

    private int getRemainingTime() {
        Duration duration = Duration.between(Instant.now(), instant);

        if (duration.isNegative()) return -1;

        return (int) duration.getSeconds();
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(0);
        out.writeVInt(index);
        out.writeVInt(getRemainingTime());
        out.writeVInt(getRemainingTime());
        out.writeVInt(0);
        out.writeDataReference(15, mapId);
        out.writeVInt(3);
        out.writeString();
        out.writeVInt(0);
        out.writeVInt(0);
    }
}
