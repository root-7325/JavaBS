package com.root7325.javabs.laser.protocol.packets.client;

import com.google.inject.Inject;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.laser.protocol.commands.LogicCommandManager;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author root7325 on 28.06.2025
 */
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class EndClientTurnMessage extends PiranhaMessage {
    private final LogicCommandManager commandManager;

    @Getter
    private List<LogicCommand> commandList = new ArrayList<>();

    @Override
    public void decode(LaserByteBuf in) {
        in.readBoolean();
        in.readVInt();
        in.readVInt();

        int count = in.readVInt();

        for (int i = 0; i < count; i++) {
            LogicCommand logicCommand = commandManager.createCommand(in.readVInt());
            if (logicCommand != null) {
                logicCommand.decode(in);
                commandList.add(logicCommand);
            }
        }
    }

    @Override
    public void encode(LaserByteBuf out) {

    }
}
