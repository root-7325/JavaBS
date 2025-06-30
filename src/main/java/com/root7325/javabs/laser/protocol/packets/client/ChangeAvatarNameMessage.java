package com.root7325.javabs.laser.protocol.packets.client;

import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Getter;

/**
 * @author root7325 on 30.06.2025
 */
@Getter
public class ChangeAvatarNameMessage extends PiranhaMessage {
    private String name;

    @Override
    public void decode(LaserByteBuf in) {
        this.name = in.readString();
    }

    @Override
    public void encode(LaserByteBuf out) {

    }
}
