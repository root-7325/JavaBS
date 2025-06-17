package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.dao.UserDAOImpl;
import com.root7325.javabs.entity.Account;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.core.ServiceLocator;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.LoginMessage;
import com.root7325.javabs.laser.protocol.packets.server.LoginOkMessage;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;
import com.root7325.javabs.laser.protocol.packets.server.ServerHelloMessage;

/**
 * @author root7325 on 17.06.2025
 */
public class LoginHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        LoginMessage loginMessage = (LoginMessage) piranhaMessage;

        UserDAOImpl userDAO = ServiceLocator.getInstance().getUserDAO();
        Account account;

        if (loginMessage.getId() == 0 && loginMessage.getToken().isEmpty()) {
            account = userDAO.createAccount();
        } else {
            account = userDAO.getAccount(loginMessage.getId(), loginMessage.getToken());
        }

        if (account != null) {
            session.writeAndFlush(new LoginOkMessage(account));
            session.writeAndFlush(new OwnHomeDataMessage(account));
        }
    }
}
