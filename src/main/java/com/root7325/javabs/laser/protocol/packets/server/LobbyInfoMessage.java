package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.core.ISessionManager;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 06.07.2025
 */
@AllArgsConstructor
public class LobbyInfoMessage extends PiranhaMessage {
    private final int online;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeVInt(online);
        out.writeVInt(0);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LobbyInfo;
    }
}
