package com.root7325.javabs.netty.server;

import com.root7325.javabs.netty.codec.PiranhaMessageDecoder;
import com.root7325.javabs.netty.codec.PiranhaMessageEncoder;
import com.root7325.javabs.netty.handler.LaserChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author root7325 on 17.06.2025
 */
public class LaserChannelInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new PiranhaMessageDecoder());
        channel.pipeline().addLast(new PiranhaMessageEncoder());
        channel.pipeline().addLast(new LaserChannelHandler());
    }
}
