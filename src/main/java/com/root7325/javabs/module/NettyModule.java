package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.root7325.javabs.laser.core.ISessionManager;
import com.root7325.javabs.laser.core.MessageRouter;
import com.root7325.javabs.laser.core.SessionManager;
import com.root7325.javabs.netty.server.LaserServer;
import com.root7325.javabs.netty.server.LaserServerBootstrap;

/**
 * @author root7325 on 22.06.2025
 */
public class NettyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ISessionManager.class).to(SessionManager.class).in(Singleton.class);
        bind(MessageRouter.class).in(Singleton.class);
        bind(LaserServer.class).in(Singleton.class);
        bind(LaserServerBootstrap.class).in(Singleton.class);
    }
}
