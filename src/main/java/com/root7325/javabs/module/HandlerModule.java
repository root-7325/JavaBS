package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.root7325.javabs.laser.handlers.*;
import com.root7325.javabs.laser.protocol.packets.MessageType;

/**
 * @author root7325 on 22.06.2025
 */
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<MessageType, IHandler> mapBinder = MapBinder.newMapBinder(
                binder(), MessageType.class, IHandler.class
        );

        mapBinder.addBinding(MessageType.ClientHello).to(ClientHelloHandler.class);
        mapBinder.addBinding(MessageType.Login).to(LoginHandler.class);
        mapBinder.addBinding(MessageType.KeepAlive).to(KeepAliveHandler.class);
        mapBinder.addBinding(MessageType.CancelMatchmaking).to(CancelMatchmakingHandler.class);
        mapBinder.addBinding(MessageType.TeamCreate).to(TeamCreateHandler.class);
        mapBinder.addBinding(MessageType.GoHomeFromOfflinePractise).to(GoHomeHandler.class);
    }
}
