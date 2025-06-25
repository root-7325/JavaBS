package com.root7325.javabs.module;

import com.google.inject.AbstractModule;

/**
 * @author root7325 on 22.06.2025
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigModule());
        install(new RulesetModule());
        install(new CryptoModule());
        install(new DatabaseModule());
        install(new NettyModule());
        install(new HandlerModule());
    }
}
