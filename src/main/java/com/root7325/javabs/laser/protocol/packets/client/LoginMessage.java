package com.root7325.javabs.laser.protocol.packets.client;

import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Getter;
import lombok.Setter;

/**
 * @author root7325 on 17.06.2025
 */
@Getter
@Setter
public class LoginMessage extends PiranhaMessage {
    private long id;
    private String token;
    private int major, minor, build;
    private String resourceSha;
    private String device;

    @Override
    public void decode(LaserByteBuf in) {
        this.id = in.readLong();
        this.token = in.readString();
        this.major = in.readInt();
        this.minor = in.readInt();
        this.build = in.readInt();
    }

    @Override
    public void encode(LaserByteBuf out) {

    }
}
