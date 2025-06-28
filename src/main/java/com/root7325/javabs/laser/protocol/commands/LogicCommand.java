package com.root7325.javabs.laser.protocol.commands;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Setter;

/**
 * @author root7325 on 28.06.2025
 */
@Setter
public abstract class LogicCommand {
    protected LaserSession laserSession;
    protected Player player;

    public void decode(LaserByteBuf in) {
        in.readVInt();
        in.readVInt();
        in.readVLong();
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(0);
        out.writeVInt(0);
        out.writeVLong(0);
    }

    public abstract int execute();
    public abstract CommandType getCommandType();
}
