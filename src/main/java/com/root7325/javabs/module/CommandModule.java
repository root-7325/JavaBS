package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.laser.protocol.commands.client.LogicSetThumbnailCommand;

/**
 * @author root7325 on 29.06.2025
 */
public class CommandModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<CommandType, LogicCommand> mapBinder = MapBinder.newMapBinder(
                binder(), CommandType.class, LogicCommand.class
        );

        mapBinder.addBinding(CommandType.SetPlayerThumbnail).to(LogicSetThumbnailCommand.class);
    }
}
