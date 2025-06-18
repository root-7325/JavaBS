package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 18.06.2025
 */
public class MatchMakingCancelledMessage extends PiranhaMessage {
    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {

    }

    @Override
    public MessageType getMessageType() {
        return MessageType.MatchMakingCancelled;
    }
}
