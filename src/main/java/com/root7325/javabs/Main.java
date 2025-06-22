package com.root7325.javabs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.root7325.javabs.config.Config;
import com.root7325.javabs.config.ServerConfig;
import com.root7325.javabs.module.AppModule;
import com.root7325.javabs.netty.server.LaserServer;
import com.root7325.javabs.netty.server.LaserServerBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    public static void main(String[] args) {
        log.info("JavaBS is starting.");

        Injector injector = Guice.createInjector(new AppModule());

        LaserServer laserServer = injector.getInstance(LaserServer.class);
        laserServer.bind();
    }
}
