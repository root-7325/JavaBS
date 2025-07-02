package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.MapBinder;
import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.laser.protocol.commands.client.LogicSetThumbnailCommand;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.laser.protocol.packets.client.*;
import com.root7325.javabs.laser.protocol.packets.ILaserServerMessageFactory;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;

/**
 * @author root7325 on 02.07.2025
 */
public class ProtocolModule extends AbstractModule {
    @Override
    protected void configure() {
        configureClientMessages();
        configureServerMessages();

        configureClientCommands();
    }

    private void configureClientMessages() {
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
        mapBinder.addBinding(MessageType.ChangeAvatarName).to(ChangeAvatarNameMessage.class);
    }

    private void configureServerMessages() {
        install(new FactoryModuleBuilder()
                .implement(OwnHomeDataMessage.class, OwnHomeDataMessage.class)
                .build(ILaserServerMessageFactory.class)
        );
    }

    private void configureClientCommands() {
        MapBinder<CommandType, LogicCommand> mapBinder = MapBinder.newMapBinder(
                binder(), CommandType.class, LogicCommand.class
        );

        mapBinder.addBinding(CommandType.SetPlayerThumbnail).to(LogicSetThumbnailCommand.class);
    }
}
