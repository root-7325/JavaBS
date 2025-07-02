package com.root7325.javabs.laser.core;

import com.google.inject.Inject;
import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * @author root7325 on 01.07.2025
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class PacketDispatcher implements IPacketDispatcher {
    private final ISessionManager sessionManager;

    @Override
    public void sendToClient(PiranhaMessage message, LaserSession session) {
        session.writeAndFlush(message);
    }

    @Override
    public void broadcast(Function<Player, PiranhaMessage> messageFunction) {
        for (LaserSession session : sessionManager.getSessions()) {
            Player player = session.getPlayer();
            if (player != null) {
                PiranhaMessage message = messageFunction.apply(player);
                session.writeAndFlush(message);
            }
        }
    }

    @Override
    public void broadcastExcept(Function<Player, PiranhaMessage> messageFunction, LaserSession exceptSession) {
        for (LaserSession session : sessionManager.getSessions()) {
            if (!exceptSession.equals(session)) {
                Player player = session.getPlayer();
                if (player != null) {
                    PiranhaMessage message = messageFunction.apply(player);
                    session.writeAndFlush(message);
                }
            }
        }
    }
}
