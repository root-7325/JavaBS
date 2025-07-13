package com.root7325.javabs.laser.protocol.packets;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;

/**
 * Factory class responsible for creating server-side complex PiranhaMessage instances.
 * Complex is understood as when we need to use DI for message params.
 *
 * @author root7325 on 28.06.2025
 */
public interface ILaserServerMessageFactory {
    OwnHomeDataMessage createOwnHomeDataMessage(Player player);
}
