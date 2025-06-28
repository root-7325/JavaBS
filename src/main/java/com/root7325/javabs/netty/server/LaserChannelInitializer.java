package com.root7325.javabs.netty.server;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.root7325.javabs.netty.codec.PiranhaMessageDecoder;
import com.root7325.javabs.netty.codec.PiranhaMessageEncoder;
import com.root7325.javabs.netty.handler.LaserChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 17.06.2025
 */
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LaserChannelInitializer extends ChannelInitializer<Channel> {
    private final Injector injector;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(injector.getInstance(PiranhaMessageDecoder.class));
        channel.pipeline().addLast(new PiranhaMessageEncoder());
        channel.pipeline().addLast(injector.getInstance(LaserChannelHandler.class));
    }
}
