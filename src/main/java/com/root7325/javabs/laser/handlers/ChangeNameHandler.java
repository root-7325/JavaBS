package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.entity.player.PlayerSettings;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.commands.server.LogicChangeAvatarNameCommand;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.ChangeAvatarNameMessage;
import com.root7325.javabs.laser.protocol.packets.server.AvailableServerCommandMessage;

/**
 * @author root7325 on 30.06.2025
 */
public class ChangeNameHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        String name = ((ChangeAvatarNameMessage) piranhaMessage).getName();

        if (isNameValid(name)) {
            PlayerSettings playerSettings = session.getPlayer().getSettings();
            playerSettings.setUsername(name);
            playerSettings.setRegistered(true);

            session.writeAndFlush(
                    new AvailableServerCommandMessage(new LogicChangeAvatarNameCommand(name))
            );
        }
    }

    private boolean isNameValid(String name) {
        return name.length() >= 3 && name.length() <= 32;
    }
}
