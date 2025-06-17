package com.root7325.javabs.laser.core;

import com.root7325.javabs.entity.Account;
import com.root7325.javabs.laser.crypto.ICrypto;
import com.root7325.javabs.laser.crypto.PepperCrypto;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class LaserSession {
    public static final AttributeKey<LaserSession> SESSION_ATTRIBUTE_KEY = AttributeKey.newInstance("session");

    private final Channel channel;

    private ICrypto crypto = new PepperCrypto();

    @Setter
    private Account account = new Account();

    public void write(PiranhaMessage... messages) {
        for (PiranhaMessage packet : messages) {
            channel.write(packet);
            log.debug("Wrote {} message!", packet.getMessageType());
        }
    }

    public void writeAndFlush(PiranhaMessage... messages) {
        write(messages);
        flush();
    }

    public void flush() {
        channel.flush();
    }

    public static LaserSession get(ChannelHandlerContext ctx) {
        return get(ctx.channel());
    }

    public static LaserSession get(Channel channel) {
        return channel.attr(SESSION_ATTRIBUTE_KEY).get();
    }
}
