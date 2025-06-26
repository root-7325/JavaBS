package com.root7325.javabs.laser.protocol.packets.client;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Getter;

/**
 * @author root7325 on 26.06.2025
 */
@Getter
public class GetPlayerProfileMessage extends PiranhaMessage {
    private long id;

    @Override
    public void decode(LaserByteBuf in) {
        this.id = in.readLong();
    }

    @Override
    public void encode(LaserByteBuf out) {

    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GetPlayerProfile;
    }
}
