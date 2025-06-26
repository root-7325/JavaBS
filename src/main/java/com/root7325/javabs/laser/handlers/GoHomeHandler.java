package com.root7325.javabs.laser.handlers;

import com.google.inject.Inject;
import com.root7325.javabs.config.game.Ruleset;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.server.KeepAliveServerMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 20.06.2025
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class GoHomeHandler implements IHandler {
    private final Ruleset ruleset;

    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        session.writeAndFlush(new OwnHomeDataMessage(session.getPlayer(), ruleset));
    }
}
