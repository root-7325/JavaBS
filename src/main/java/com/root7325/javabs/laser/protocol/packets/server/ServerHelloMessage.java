package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import io.netty.buffer.ByteBuf;

import java.security.SecureRandom;

/**
 * @author root7325 on 17.06.2025
 */
public class ServerHelloMessage extends PiranhaMessage {

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        byte[] randBytes = new byte[24];
        new SecureRandom().nextBytes(randBytes);

        out.writeBytes(randBytes);
    }


    @Override
    public MessageType getMessageType() {
        return MessageType.ServerHello;
    }
}
