package com.root7325.javabs.laser.protocol.commands;

import com.google.inject.Inject;
import com.root7325.javabs.module.ProtocolModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Factory class responsible for creating client-side LogicCommand instances.
 *
 * @author root7325 on 28.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LogicCommandManager {
    /**
     * Mapping of message types to their corresponding LogicCommand instances.
     * This map is populated via DI in {@link ProtocolModule}.
     */
    private final Map<CommandType, LogicCommand> commandMap;

    /**
     * Creates a new command object from given message type.
     *
     * @param type of required command
     * @return a new command if type is mapped, {@code null} otherwise
     */
    public LogicCommand createCommand(int type) {
        CommandType commandType = CommandType.from(type);

        LogicCommand logicCommand = commandMap.get(commandType);
        if (logicCommand != null) {
            log.trace("Created {} command!", commandType);
            return logicCommand;
        } else {
            log.trace("Unknown command {}!", type);
            return null;
        }
    }
}
