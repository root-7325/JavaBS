package com.root7325.javabs.laser.crypto;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Interface for cryptographic operations.
 * It provides two main methods: for encryption and decryption of data.
 *
 * @author root7325 on 11.01.2025
 */
public interface ICrypto {
    /**
     * Decrypts provided data.
     *
     * @param allocator buffer allocator used for allocating memory for the decrypted data.
     * @param messageId id of message to decrypt
     * @param encrypted buffer containing encrypted data.
     * @return a buffer containing the decrypted data.
     */
    ByteBuf decrypt(ByteBufAllocator allocator, int messageId, ByteBuf encrypted);

    /**
     * Encrypts provided data.
     *
     * @param allocator   buffer allocator used for allocating memory for the encrypted data.
     * @param messageType type of message to encrypt
     * @param plain       buffer containing plaintext (unencrypted) data.
     * @return a buffer containing encrypted data.
     */
    ByteBuf encrypt(ByteBufAllocator allocator, MessageType messageType, ByteBuf plain);
}
