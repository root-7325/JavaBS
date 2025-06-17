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

    public void bind(String host, int port) {
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
