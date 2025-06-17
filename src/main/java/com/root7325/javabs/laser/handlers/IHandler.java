package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;

/**
 * @author root7325 on 17.06.2025
 */
public interface IHandler {
    void handle(PiranhaMessage piranhaMessage, LaserSession session);
}
