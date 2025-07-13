package com.root7325.javabs.laser.protocol.packets;

import com.root7325.javabs.utils.LaserByteBuf;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for all SC protocol messages.
 *
 * @author root7325 on 17.06.2025
 */
@Setter
@Getter
public abstract class PiranhaMessage {
    /** Constant of message header size in bytes. */
    public static final int HEADER_SIZE = 7;

    /** Type of this message. */
    private MessageType messageType;

    /**
     * Decodes message payload from input buffer.
     *
     * @param in input buffer containing encoded message data
     */
    public abstract void decode(LaserByteBuf in);

    /**
     * Encodes message payload to output buffer.
     *
     * @param out output buffer where encoded message data will be written
     */
    public abstract void encode(LaserByteBuf out);
}
