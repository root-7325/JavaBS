package com.root7325.javabs.laser.protocol.commands;

import com.google.inject.Inject;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author root7325 on 28.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LogicCommandManager {
    private final Map<CommandType, LogicCommand> commandMap;

    public LogicCommand createCommand(int type) {
        CommandType commandType = CommandType.from(type);

        LogicCommand logicCommand = commandMap.get(commandType);
        if (logicCommand != null) {
            log.debug("Created {} command!", commandType);
            return logicCommand;
        } else {
            log.debug("Unknown command {}!", type);
            return null;
        }
    }
}
