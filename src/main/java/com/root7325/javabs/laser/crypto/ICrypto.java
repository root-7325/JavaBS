package com.root7325.javabs.laser.crypto;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author root7325 on 11.01.2025
 */
public interface ICrypto {
    ByteBuf decrypt(ByteBufAllocator allocator, int messageId, ByteBuf encrypted);
    ByteBuf encrypt(ByteBufAllocator allocator, MessageType messageType, ByteBuf plain);
}
