package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 18.06.2025
 */
@AllArgsConstructor
public class TeamErrorMessage extends PiranhaMessage {
    private final int code;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeVInt(code);
        out.writeVInt(code);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.TeamError;
    }
}
