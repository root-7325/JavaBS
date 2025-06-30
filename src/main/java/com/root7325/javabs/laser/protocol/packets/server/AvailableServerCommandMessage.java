package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 30.06.2025
 */
@AllArgsConstructor
public class AvailableServerCommandMessage extends PiranhaMessage {
    private final LogicCommand command;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeVInt(command.getCommandType().getI());
        command.encode(out);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.AvailableServerCommand;
    }
}
