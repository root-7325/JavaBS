package com.root7325.javabs.laser.crypto;

import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.utils.libraries.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

import java.util.Arrays;
import java.util.HexFormat;

/**
 * @author root7325 on 11.01.2025
 * <p>
 * todo: rewrite this for God sake..
 */
public class PepperCrypto implements ICrypto {
    private static final byte[] SERVER_KEY = HexFormat.of().parseHex("03BBE171010D8F928E7B3B71773CCBEF75AE4B02E6D7B11BC760ED3AE853C839");

    public byte[] key = new byte[32];
    public Nonce serverNonce;
    public Nonce clientNonce;
    public byte[] clientSecretKey = HexFormat.of().parseHex("BB14D6FD2B7C9823EAEDB4338CB7237F61E422D23C4977F74ADA052702C0C62D");
    public byte[] clientPublicKey = new byte[32];
    public byte[] sessionKey = new byte[24];
    public byte[] sharedKey = new byte[32];

    public TweetNacl.Box box;

    public PepperCrypto() {
        this.clientNonce = new Nonce();
        this.serverNonce = new Nonce();
        TweetNacl.crypto_scalarmult_base(clientPublicKey, clientSecretKey);
        TweetNacl.randombytes(sharedKey, 32);
    }

    @Override
    public ByteBuf encrypt(ByteBufAllocator allocator, MessageType messageType, ByteBuf plain, byte[] key) {
        switch (messageType) {
            case ServerHello:
                return plain;
            case LoginOk:
                byte[] payload = ByteBufUtil.getBytes(plain);
                Nonce nonce = new Nonce(clientNonce.bytes(), clientPublicKey, SERVER_KEY);

                byte[] midPayload = concat(serverNonce.bytes(), sharedKey);
                payload = concat(midPayload, payload);
                byte[] encrypted = new TweetNacl.Box(SERVER_KEY, clientSecretKey).after(payload, nonce.bytes());

                this.box = new TweetNacl.Box(SERVER_KEY, clientPublicKey);
                this.box.sharedKey = sharedKey;
                return toBuf(allocator, encrypted);
            case LoginFailed:
                if (box == null) return plain;
            default:
                serverNonce.increment();
                payload = ByteBufUtil.getBytes(plain);

                encrypted = box.after(payload, serverNonce.bytes());

                return toBuf(allocator, encrypted);
        }
    }

    @Override
    public ByteBuf decrypt(ByteBufAllocator allocator, int messageId, ByteBuf encrypted, byte[] key) {
        switch (messageId) {
            case 10100:
                return encrypted;
            case 10101:
                byte[] payload = ByteBufUtil.getBytes(encrypted);

                byte[] inPublicKey = Arrays.copyOfRange(payload, 0, 32);
                payload = Arrays.copyOfRange(payload, 32, payload.length);

                Nonce nonce = new Nonce(clientPublicKey, SERVER_KEY);

                byte[] decrypted = new TweetNacl.Box(SERVER_KEY, clientSecretKey)
                        .open(payload, nonce.bytes());

                sessionKey = Arrays.copyOfRange(decrypted, 0, 24);
                clientNonce = new Nonce(Arrays.copyOfRange(decrypted, 24, 48));

                decrypted = Arrays.copyOfRange(decrypted, 48, decrypted.length);
                return toBuf(allocator, decrypted);
            default:
                clientNonce.increment();
                payload = ByteBufUtil.getBytes(encrypted);

                decrypted = box.open_after(payload, clientNonce.bytes());
                return toBuf(allocator, decrypted);
        }
    }

    private static ByteBuf toBuf(ByteBufAllocator allocator, byte[] bytes) {
        ByteBuf out = allocator.buffer(bytes.length);
        return out.writeBytes(bytes);
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}