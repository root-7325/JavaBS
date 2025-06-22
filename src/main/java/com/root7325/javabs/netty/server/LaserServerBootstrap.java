package com.root7325.javabs.netty.server;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Builder;

/**
 * @author root7325 on 17.06.2025
 */
@Builder
public class LaserServerBootstrap {
    private final int bossThreads = 1;
    private final int workerThreads = 1;
    private final int soBacklog = 100;
    private final Injector injector;

    @Inject
    public LaserServerBootstrap(Injector injector) {
        this.injector = injector;
    }

    public ServerBootstrap create() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        return new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, soBacklog)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(injector.getInstance(LaserChannelInitializer.class));
    }
}
