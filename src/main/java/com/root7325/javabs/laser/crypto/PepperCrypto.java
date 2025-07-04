package com.root7325.javabs.laser.crypto;

import com.google.inject.Inject;
import com.root7325.javabs.config.server.CryptoConfig;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.utils.libraries.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HexFormat;

/**
 * @author root7325 on 11.01.2025
 */
@Slf4j
public class PepperCrypto implements ICrypto {
    private final byte[] serverKey;
    private final byte[] clientSecretKey;

    private final byte[] clientPublicKey = new byte[32];
    private final byte[] sharedKey = new byte[32];

    private final Nonce serverNonce;
    private Nonce clientNonce;

    public TweetNacl.Box box;

    @Inject
    public PepperCrypto(CryptoConfig cryptoConfig) {
        this.serverKey = HexFormat.of().parseHex(cryptoConfig.getServerKey());
        this.clientSecretKey = HexFormat.of().parseHex(cryptoConfig.getClientSecretKey());

        this.serverNonce = new Nonce();
        this.clientNonce = new Nonce();

        TweetNacl.crypto_scalarmult_base(clientPublicKey, clientSecretKey);
        TweetNacl.randombytes(sharedKey, 32);
    }

    @Override
    public ByteBuf decrypt(ByteBufAllocator allocator, int messageId, ByteBuf encrypted) {
        try {
            switch (messageId) {
                case 10100 -> {
                    return encrypted.retainedSlice();
                }
                case 10101 -> {
                    byte[] payload = ByteBufUtil.getBytes(encrypted);
                    payload = Arrays.copyOfRange(payload, 32, payload.length);

                    Nonce nonce = new Nonce(clientPublicKey, serverKey);

                    byte[] decrypted = new TweetNacl.Box(serverKey, clientSecretKey)
                            .open(payload, nonce.bytes());

                    clientNonce = new Nonce(Arrays.copyOfRange(decrypted, 24, 48));

                    decrypted = Arrays.copyOfRange(decrypted, 48, decrypted.length);
                    return toBuf(allocator, decrypted);
                }
                default -> {
                    clientNonce.increment();
                    byte[] payload = ByteBufUtil.getBytes(encrypted);

                    byte[] decrypted = box.open_after(payload, clientNonce.bytes());
                    return toBuf(allocator, decrypted);
                }
            }
        } finally {
            encrypted.release();
        }
    }

    @Override
    public ByteBuf encrypt(ByteBufAllocator allocator, MessageType messageType, ByteBuf plain) {
        try {
            switch (messageType) {
                case ServerHello -> {
                    return plain.retainedSlice();
                }
                case LoginOk -> {
                    byte[] payload = ByteBufUtil.getBytes(plain);
                    Nonce nonce = new Nonce(clientNonce.bytes(), clientPublicKey, serverKey);

                    byte[] midPayload = concat(serverNonce.bytes(), sharedKey);
                    payload = concat(midPayload, payload);
                    byte[] encrypted = new TweetNacl.Box(serverKey, clientSecretKey).after(payload, nonce.bytes());

                    this.box = new TweetNacl.Box(serverKey, clientPublicKey);
                    this.box.sharedKey = sharedKey;
                    return toBuf(allocator, encrypted);
                }
                default -> {
                    if (messageType == MessageType.LoginFailed && box == null) {
                        return plain.retainedSlice();
                    }

                    serverNonce.increment();
                    byte[] payload = ByteBufUtil.getBytes(plain);

                    byte[] encrypted = box.after(payload, serverNonce.bytes());

                    return toBuf(allocator, encrypted);
                }
            }
        } finally {
            plain.release();
        }
    }

    private static ByteBuf toBuf(ByteBufAllocator allocator, byte[] bytes) {
        ByteBuf out = allocator.buffer(bytes.length);
        return out.writeBytes(bytes);
    }

    private static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}