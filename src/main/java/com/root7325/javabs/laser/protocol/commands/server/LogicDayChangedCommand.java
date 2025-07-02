package com.root7325.javabs.laser.protocol.commands.server;

import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 01.07.2025
 */
@AllArgsConstructor
public class LogicDayChangedCommand extends LogicCommand {
    private final OwnHomeDataMessage message;

    @Override
    public void encode(LaserByteBuf out) {
        out.writeBoolean(true);
        message.encodeDailyData(out);

        out.writeBoolean(true);
        message.encodeConfData(out);

        super.encode(out);
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DayChanged;
    }
}
