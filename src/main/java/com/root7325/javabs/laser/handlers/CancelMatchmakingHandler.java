package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.server.MatchMakingCancelledMessage;

/**
 * @author root7325 on 18.06.2025
 */
public class CancelMatchmakingHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        session.writeAndFlush(new MatchMakingCancelledMessage());
    }
}
