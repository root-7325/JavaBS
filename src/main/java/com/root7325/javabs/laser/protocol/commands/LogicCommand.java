package com.root7325.javabs.laser.protocol.commands;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Setter;

/**
 * Abstract base class for all SC protocol commands.
 *
 * @author root7325 on 28.06.2025
 */
@Setter
public abstract class LogicCommand {
    protected LaserSession laserSession;
    protected Player player;

    /**
     * Decodes command payload from input buffer.
     *
     * @param in input buffer containing encoded command data
     */
    public void decode(LaserByteBuf in) {
        in.readVInt();
        in.readVInt();
        in.readVLong();
    }

    /**
     * Encodes command payload to output buffer.
     *
     * @param out output buffer where encoded command data will be written
     */
    public void encode(LaserByteBuf out) {
        out.writeVInt(0);
        out.writeVInt(0);
        out.writeVLong(0);
    }

    /**
     * Executes this command.
     *
     * @return {@code 0} if executed successfully, {@code -1} otherwise
     */
    public abstract int execute();

    /**
     * Returns this command type.
     *
     * @return type of this command
     */
    public abstract CommandType getCommandType();
}
