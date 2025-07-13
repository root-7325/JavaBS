package com.root7325.javabs.laser.core;

import com.google.inject.Inject;
import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.crypto.ICrypto;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a client session.
 *
 * @author root7325 on 17.06.2025
 */
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class LaserSession {
    /** Netty attribute key for storing session instance on the channel. */
    public static final AttributeKey<LaserSession> SESSION_ATTRIBUTE_KEY = AttributeKey.newInstance("session");

    @Setter
    private Channel channel;

    private final ICrypto crypto;

    @Setter
    private Player player;

    /**
     * Writes one or more messages to the client channel without flushing.
     *
     * @param messages messages to write to client
     */
    public void write(PiranhaMessage... messages) {
        for (PiranhaMessage packet : messages) {
            channel.write(packet);
            log.debug("Wrote {} message!", packet.getMessageType());
        }
    }

    /**
     * Writes and immediately flushes one or more messages to client.
     *
     * @param messages messages to write and flush to client
     */
    public void writeAndFlush(PiranhaMessage... messages) {
        channel.eventLoop().execute(() -> {
            write(messages);
            flush();
        });
    }

    /**  Flushes any pending messages in channel's write buffer to client. */
    public void flush() {
        channel.flush();
    }

    /**
     * Retrieves LaserSession instance from a Netty ChannelHandlerContext.
     *
     * @param ctx Netty channel handler context
     * @return LaserSession instance associated with channel, or null if not found
     */
    public static LaserSession get(ChannelHandlerContext ctx) {
        return get(ctx.channel());
    }

    /**
     * Retrieves LaserSession instance from a Netty Channel.
     *
     * @param channel Netty channel
     * @return LaserSession instance associated with channel, or null if not found
     */
    public static LaserSession get(Channel channel) {
        return channel.attr(SESSION_ATTRIBUTE_KEY).get();
    }
}
