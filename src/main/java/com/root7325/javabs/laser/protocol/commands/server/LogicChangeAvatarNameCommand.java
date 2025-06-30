package com.root7325.javabs.laser.protocol.commands.server;

import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 30.06.2025
 */
@AllArgsConstructor
public class LogicChangeAvatarNameCommand extends LogicCommand {
    private final String name;

    @Override
    public void encode(LaserByteBuf out) {
        out.writeString(name);
        out.writeVInt(0);
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ChangeAvatarName;
    }
}
