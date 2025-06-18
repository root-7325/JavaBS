package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 17.06.2025
 */
@AllArgsConstructor
public class LoginFailedMessage extends PiranhaMessage {
    private final String reason;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeInt(1);
        out.writeString();
        out.writeString();
        out.writeString();
        out.writeString();
        out.writeString(reason);
        out.writeInt(0);
        out.writeBoolean(false);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LoginFailed;
    }
}
