package com.root7325.javabs.laser.core;

import com.google.inject.Inject;
import com.root7325.javabs.laser.handlers.*;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.module.HandlerModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Routes incoming Piranha messages to appropriate handlers based on message type.
 * 
 * @author root7325 on 17.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class MessageRouter {
    /**
     * Mapping of message types to their corresponding IHandler instances.
     * This map is populated via DI in {@link HandlerModule}.
     */
    private final Map<MessageType, IHandler> handlers;
    private final ExecutorService executorService;

    /**
     * Handles an incoming Piranha message by routing it to appropriate handler.
     *
     * @param piranhaMessage message to be handled
     * @param session session associated with message
     */
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        IHandler handler = handlers.get(piranhaMessage.getMessageType());
        if (handler != null) {
            log.debug("Message handled by {}", handler.getClass().getSimpleName());

            executorService.submit(() -> {
                try {
                    handler.handle(piranhaMessage, session);
                } catch (Exception ex) {
                    log.error("Error handling message {} by {}", piranhaMessage, handler.getClass().getSimpleName());
                }
            });
        } else {
            log.warn("No handler for {}", piranhaMessage.getMessageType());
        }
    }
}
