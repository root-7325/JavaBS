package com.root7325.javabs.laser.core;

import com.google.inject.Inject;
import com.root7325.javabs.laser.handlers.*;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class MessageRouter {
    private final Map<MessageType, IHandler> handlers;

    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        IHandler handler = handlers.get(piranhaMessage.getMessageType());
        if (handler != null) {
            log.debug("Message handled by {}", handler.getClass().getSimpleName());
            handler.handle(piranhaMessage, session);
        } else {
            log.warn("No handler for {}", piranhaMessage.getMessageType());
        }
    }
}
