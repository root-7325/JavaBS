package com.root7325.javabs.laser.handlers;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.EndClientTurnMessage;
import com.root7325.javabs.laser.protocol.packets.server.KeepAliveServerMessage;

/**
 * @author root7325 on 17.06.2025
 */
public class EctHandler implements IHandler {
    @Override
    public void handle(PiranhaMessage piranhaMessage, LaserSession session) {
        EndClientTurnMessage ectMessage = (EndClientTurnMessage) piranhaMessage;

        ectMessage.getCommandList()
                .forEach(logicCommand -> {
                    logicCommand.setLaserSession(session);
                    logicCommand.setPlayer(session.getPlayer());
                    logicCommand.execute();
                });
    }
}
