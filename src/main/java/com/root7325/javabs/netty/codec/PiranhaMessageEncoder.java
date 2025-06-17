package com.root7325.javabs.netty.codec;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.crypto.ICrypto;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author root7325 on 17.06.2025
 */
public class PiranhaMessageEncoder extends MessageToByteEncoder<PiranhaMessage> {
    private ICrypto crypto;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PiranhaMessage piranhaMessage, ByteBuf byteBuf) throws Exception {
        if (crypto == null) {
            crypto = LaserSession.get(channelHandlerContext).getCrypto();
        }

        ByteBuf payload = channelHandlerContext.alloc().buffer();
        LaserByteBuf laserByteBuf = new LaserByteBuf(payload);
        piranhaMessage.encode(laserByteBuf);

        ByteBuf encrypted = crypto.encrypt(channelHandlerContext.alloc(), piranhaMessage.getMessageType(), payload);

        byteBuf.writeShort(piranhaMessage.getMessageType().getI());
        byteBuf.writeMedium(encrypted.writerIndex());
        byteBuf.writeShort(0);
        byteBuf.writeBytes(encrypted);
        byteBuf.writeBytes(new byte[7]);
    }
}
