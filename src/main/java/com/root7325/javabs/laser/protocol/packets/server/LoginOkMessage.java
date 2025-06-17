package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.entity.Player;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 17.06.2025
 */
@AllArgsConstructor
public class LoginOkMessage extends PiranhaMessage {
    private final Player player;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeInt(0);
        out.writeInt(player.getId());

        out.writeInt(0);
        out.writeInt(player.getId());

        out.writeString(player.getToken());
        out.writeString();
        out.writeString();

    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LoginOk;
    }
}
