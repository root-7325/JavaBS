package com.root7325.javabs.laser.core;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;

import java.util.function.Function;

/**
 * @author root7325 on 01.07.2025
 */
public interface IPacketDispatcher {
    void sendToClient(PiranhaMessage message, LaserSession session);
    void broadcast(Function<Player, PiranhaMessage> messageFunction);
    void broadcastExcept(Function<Player, PiranhaMessage> messageFunction, LaserSession exceptSession);
}
