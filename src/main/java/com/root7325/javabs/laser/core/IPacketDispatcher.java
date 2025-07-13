package com.root7325.javabs.laser.core;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;

import java.util.function.Function;

/**
 * Interface for dispatching network packets to clients.
 * 
 * @author root7325 on 01.07.2025
 */
public interface IPacketDispatcher {
    /**
     * Sends a PiranhaMessage to a specific client session.
     * 
     * @param message message to send to client
     * @param session client session to send message to
     */
    void sendToClient(PiranhaMessage message, LaserSession session);
    
    /**
     * Broadcasts a message to all connected players.
     * Message is generated for each player using provided function.
     * 
     * @param messageFunction function that creates a message for each player
     */
    void broadcast(Function<Player, PiranhaMessage> messageFunction);
    
    /**
     * Broadcasts a message to all connected players except the specified session.
     * Message is generated for each player using the provided function.
     * 
     * @param messageFunction function that creates a message for each player
     * @param exceptSession session to exclude from the broadcast
     */
    void broadcastExcept(Function<Player, PiranhaMessage> messageFunction, LaserSession exceptSession);
}
