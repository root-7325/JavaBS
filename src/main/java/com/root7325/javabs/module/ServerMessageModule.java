package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.root7325.javabs.laser.protocol.packets.ILaserServerMessageFactory;
import com.root7325.javabs.laser.protocol.packets.server.OwnHomeDataMessage;

/**
 * @author root7325 on 28.06.2025
 */
public class ServerMessageModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(OwnHomeDataMessage.class, OwnHomeDataMessage.class)
                .build(ILaserServerMessageFactory.class)
        );
    }
}
