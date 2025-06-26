package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.GetPlayerProfileMessage;
import com.root7325.javabs.laser.protocol.packets.server.PlayerProfileMessage;

/**
 * @author root7325 on 26.06.2025
 */
public class PlayerProfileHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        session.writeAndFlush(new PlayerProfileMessage(session.getPlayer()));
    }
}
