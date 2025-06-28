package com.root7325.javabs.laser.protocol.packets;

import com.google.inject.Inject;
import com.root7325.javabs.laser.protocol.packets.client.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LaserMessageFactory {
    private final Map<MessageType, PiranhaMessage> messageMap;

    public PiranhaMessage create(MessageType messageType) {
        PiranhaMessage piranhaMessage = messageMap.get(messageType);
        if (piranhaMessage == null) {
            return null;
        }

        piranhaMessage.setMessageType(messageType);
        return piranhaMessage;
    }
}
