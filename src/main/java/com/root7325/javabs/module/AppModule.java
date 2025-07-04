package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author root7325 on 22.06.2025
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigModule());
        install(new AssetModule());
        install(new CryptoModule());
        install(new DatabaseModule());
        install(new NettyModule());
        install(new EventGenerationModule());
        install(new ProtocolModule());
        install(new HandlerModule());
    }

    @Provides
    private ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    private ScheduledExecutorService provideScheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
