package com.root7325.javabs.netty.codec;

import com.root7325.javabs.laser.core.LaserSession;
import com.root7325.javabs.laser.crypto.ICrypto;
import com.root7325.javabs.laser.protocol.packets.LaserMessageFactory;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@RequiredArgsConstructor
public class PiranhaMessageDecoder extends ByteToMessageDecoder {
    private final LaserMessageFactory messageFactory;
    private ICrypto crypto;

    public PiranhaMessageDecoder() {
        this.messageFactory = new LaserMessageFactory();
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (crypto == null) {
            crypto = LaserSession.get(channelHandlerContext).getCrypto();
        }

        if (!hasEnoughBytesForHeader(byteBuf)) {
            return;
        }

        MessageHeader messageHeader = readMessageHeader(byteBuf);
        if (!hasEnoughBytesForBody(byteBuf, messageHeader.getLength())) {
            internalBuffer().resetReaderIndex();
            return;
        }

        ByteBuf in = readBody(byteBuf, messageHeader);
        ByteBuf decrypted = crypto.decrypt(channelHandlerContext.alloc(), messageHeader.getType(), in, null);

        processPacket(messageHeader.getType(), decrypted, list);
    }

    private boolean hasEnoughBytesForHeader(ByteBuf byteBuf) {
        return byteBuf.readableBytes() >= PiranhaMessage.HEADER_SIZE;
    }

    private MessageHeader readMessageHeader(ByteBuf in) {
        short type = in.readShort();
        int length = in.readMedium();
        short version = in.readShort();

        return new MessageHeader(type, length, version);
    }

    private boolean hasEnoughBytesForBody(ByteBuf byteBuf, int length) {
        return byteBuf.readableBytes() >= length;
    }

    private ByteBuf readBody(ByteBuf in, MessageHeader messageHeader) {
        ByteBuf payload = in.readBytes(messageHeader.getLength());
        return payload;
    }

    private void processPacket(int type, ByteBuf in, List<Object> list) {
        MessageType messageType = getPacketType(type);
        if (messageType == null) {
            return;
        }

        PiranhaMessage piranhaMessage = messageFactory.create(messageType);
        if (piranhaMessage != null) {
            LaserByteBuf laserByteBuf = new LaserByteBuf(in);
            piranhaMessage.decode(laserByteBuf);

            list.add(piranhaMessage);
            log.debug("Decoded {} message!", messageType);
        } else {
            log.debug("Unknown message {}!", type);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class MessageHeader {
        private final short type;
        private final int length;
        private final short version;
    }

    private MessageType getPacketType(int type) {
        try {
            return MessageType.from(type);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
