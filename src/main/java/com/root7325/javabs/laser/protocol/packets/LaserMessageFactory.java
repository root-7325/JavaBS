package com.root7325.javabs.laser.protocol.packets;

import com.google.inject.Inject;
import com.root7325.javabs.module.ProtocolModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory class responsible for creating client-side PiranhaMessage instances.
 *
 * @author root7325 on 17.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LaserMessageFactory {
    /**
     * Mapping of message types to their corresponding PiranhaMessage instances.
     * This map is populated via DI in {@link ProtocolModule}.
     */
    private final Map<MessageType, PiranhaMessage> messageMap;

    /**
     * Creates a new message object from given message type.
     *
     * @param messageType type of required message
     * @return a new message if type is mapped, {@code null} otherwise
     */
    public PiranhaMessage create(MessageType messageType) {
        PiranhaMessage piranhaMessage = messageMap.get(messageType);
        if (piranhaMessage == null) {
            return null;
        }

        piranhaMessage.setMessageType(messageType);
        return piranhaMessage;
    }
}
