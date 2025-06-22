package com.root7325.javabs.netty.handler;

import com.google.inject.Inject;
import com.root7325.javabs.laser.core.ISessionManager;
import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.core.MessageRouter;
import com.root7325.javabs.laser.core.SessionManager;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class LaserChannelHandler extends ChannelInboundHandlerAdapter {
    private final MessageRouter router;
    private final ISessionManager sessionManager;
    private LaserSession laserSession;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} is active!", ctx.channel().remoteAddress());

        this.laserSession.setChannel(ctx.channel());
        ctx.channel().attr(LaserSession.SESSION_ATTRIBUTE_KEY).set(laserSession);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} is inactive!", ctx.channel().remoteAddress());

        sessionManager.removeSession(laserSession);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }

        if (msg instanceof PiranhaMessage) {
            log.debug("Handling PiranhaMessage.");
            router.handle((PiranhaMessage) msg, laserSession);
        }

        laserSession.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Exception caught!", cause);
    }
}
