package com.root7325.javabs.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@RequiredArgsConstructor
public class LaserServer {
    private final ServerBootstrap serverBootstrap;

    public void bind(int port) {
        log.debug("Binding to :{}...", port);
        ChannelFuture channelFuture = serverBootstrap.bind(port);
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                log.info("Server successfully bound to port {}!", port);
            } else {
                log.error("Failed to bind to port {}.", port, future.cause());
            }
        });
    }
}
