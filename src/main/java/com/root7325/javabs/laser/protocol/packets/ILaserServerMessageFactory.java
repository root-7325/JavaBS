package com.root7325.javabs.laser.protocol.packets;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;

/**
 * @author root7325 on 28.06.2025
 */
public interface ILaserServerMessageFactory {
    OwnHomeDataMessage createOwnHomeDataMessage(Player player);
}
