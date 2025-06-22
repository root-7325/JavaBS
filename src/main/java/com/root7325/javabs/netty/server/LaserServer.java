package com.root7325.javabs.netty.server;

import com.google.inject.Inject;
import com.root7325.javabs.config.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
public class LaserServer {
    private final String host;
    private final int port;
    private final ServerBootstrap serverBootstrap;

    @Inject
    public LaserServer(ServerConfig serverConfig, LaserServerBootstrap laserServerBootstrap) {
        this.host = serverConfig.getHost();
        this.port = serverConfig.getPort();

        this.serverBootstrap = laserServerBootstrap.create();
    }

    public void bind() {
        log.debug("Binding to {}:{}...", host, port);
        ChannelFuture channelFuture = serverBootstrap.bind(host, port);
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                log.info("Server successfully bound to {}:{}!", host, port);
            } else {
                log.error("Failed to bind to {}:{}.", host, port, future.cause());
            }
        });
    }
}
