package com.root7325.javabs.laser.crypto;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author root7325 on 17.06.2025
 */
public class NopCrypto implements ICrypto {
    @Override
    public ByteBuf decrypt(ByteBufAllocator allocator, int messageId, ByteBuf encrypted) {
        return encrypted;
    }

    @Override
    public ByteBuf encrypt(ByteBufAllocator allocator, MessageType messageType, ByteBuf plain) {
        return plain;
    }
}
