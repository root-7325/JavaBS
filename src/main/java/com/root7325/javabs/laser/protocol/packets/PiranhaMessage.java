package com.root7325.javabs.laser.protocol.packets;

import com.root7325.javabs.utils.LaserByteBuf;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

/**
 * @author root7325 on 17.06.2025
 */
@Setter
@Getter
public abstract class PiranhaMessage {
    public static final int HEADER_SIZE = 7;

    private MessageType messageType;

    public abstract void decode(LaserByteBuf in);
    public abstract void encode(LaserByteBuf out);
}
