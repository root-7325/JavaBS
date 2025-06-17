package com.root7325.javabs;

import com.root7325.javabs.config.Config;
import com.root7325.javabs.config.ServerConfig;
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
    public static void main(String[] args) {
        log.info("JavaBS is starting.");

        Config config = Config.getInstance();
        ServerConfig serverConfig = config.getServerConfig();
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        ServerBootstrap serverBootstrap = LaserServerBootstrap.create();
        LaserServer laserServer = new LaserServer(serverBootstrap);

        laserServer.bind(serverConfig.getHost(), serverConfig.getPort());
    }
}
