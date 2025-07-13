package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;

/**
 * Handler interface for processing incoming Piranha messages.
 * 
 * @author root7325 on 17.06.2025
 */
public interface IHandler {
    /**
     * Handles an incoming Piranha message for given session.
     * 
     * @param piranhaMessage message to process
     * @param session session associated with message
     */
    void handle(PiranhaMessage piranhaMessage, LaserSession session);
}
