package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.server.KeepAliveServerMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;

/**
 * @author root7325 on 20.06.2025
 */
public class GoHomeHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        session.writeAndFlush(new OwnHomeDataMessage(session.getPlayer()));
    }
}
