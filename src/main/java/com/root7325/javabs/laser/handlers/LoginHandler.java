package com.root7325.javabs.laser.handlers;

import com.google.inject.Inject;
import com.root7325.javabs.config.game.Ruleset;
import com.root7325.javabs.dao.PlayerDAO;
import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.core.ISessionManager;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.logic.event.EventManager;
import com.root7325.javabs.laser.protocol.packets.ILaserServerMessageFactory;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.LoginMessage;
import com.root7325.javabs.laser.protocol.packets.server.LoginFailedMessage;
import com.root7325.javabs.laser.protocol.packets.server.LoginOkMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 17.06.2025
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LoginHandler implements IHandler {
    private final ILaserServerMessageFactory serverMessageFactory;
    private final PlayerDAO playerDAO;

    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        LoginMessage loginMessage = (LoginMessage) piranhaMessage;

        if (loginMessage.getId() == 0 && loginMessage.getToken().isEmpty()) {
            playerDAO.createPlayer()
                    .thenAccept(player -> handleLogin(session, player));
        } else {
            playerDAO.getPlayer(loginMessage.getId(), loginMessage.getToken())
                    .thenAccept(player -> handleLogin(session, player));
        }
    }

    private void handleLogin(LaserSession session, Player player) {
        if (player != null) {
            session.setPlayer(player);

            session.writeAndFlush(
                    new LoginOkMessage(player),
                    serverMessageFactory.createOwnHomeDataMessage(player)
            );
        } else {
            session.writeAndFlush(new LoginFailedMessage("Account not found."));
        }
    }
}
