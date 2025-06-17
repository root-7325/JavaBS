package com.root7325.javabs;

import com.root7325.javabs.laser.core.ServiceLocator;
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
    private static final int SERVER_PORT = 9339;

    public static void main(String[] args) {
        log.info("JavaBS is starting.");

        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        ServerBootstrap serverBootstrap = LaserServerBootstrap.create();
        LaserServer laserServer = new LaserServer(serverBootstrap);

        laserServer.bind(SERVER_PORT);
    }
}
