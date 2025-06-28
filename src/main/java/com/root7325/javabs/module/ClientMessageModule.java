package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.*;

import java.util.function.Supplier;

/**
 * @author root7325 on 28.06.2025
 */
public class ClientMessageModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<MessageType, PiranhaMessage> mapBinder = MapBinder.newMapBinder(
                binder(),
                MessageType.class,
                PiranhaMessage.class
        );

        mapBinder.addBinding(MessageType.ClientHello).to(ClientHelloMessage.class);
        mapBinder.addBinding(MessageType.Login).to(LoginMessage.class);
        mapBinder.addBinding(MessageType.KeepAlive).to(KeepAliveMessage.class);
        mapBinder.addBinding(MessageType.CancelMatchmaking).to(CancelMatchmakingMessage.class);
        mapBinder.addBinding(MessageType.TeamCreate).to(TeamCreateMessage.class);
        mapBinder.addBinding(MessageType.GoHomeFromOfflinePractise).to(GoHomeFromOfflinePractiseMessage.class);
        mapBinder.addBinding(MessageType.GetPlayerProfile).to(GetPlayerProfileMessage.class);
        mapBinder.addBinding(MessageType.EndClientTurn).to(EndClientTurnMessage.class);
    }
}
