package com.root7325.javabs.laser.protocol.commands.client;

import com.root7325.javabs.laser.logic.common.GlobalId;
import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.utils.LaserByteBuf;

/**
 * @author root7325 on 29.06.2025
 */
public class LogicSetThumbnailCommand extends LogicCommand {
    private GlobalId thumbnail;

    @Override
    public void decode(LaserByteBuf in) {
        super.decode(in);
        this.thumbnail = in.readDataReference();
    }


    @Override
    public int execute() {
        player.getSettings().setThumbnail(thumbnail);
        return 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SetPlayerThumbnail;
    }
}
