/*
 * Copyright (c) 2000 - 2020 The Legion of the Bouncy Castle Inc. (https://www.bouncycastle.org)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/*
 * The MIT License
 *
 * Copyright (C) 2020 Shamil
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.root7325.javabs.utils.libraries;

/*  The BLAKE2 cryptographic hash function was designed by Jean-
 Philippe Aumasson, Samuel Neves, Zooko Wilcox-O'Hearn, and Christian
 Winnerlein.

 Reference Implementation and Description can be found at: https://blake2.net/
 Internet Draft: https://tools.ietf.org/html/draft-saarinen-blake2-02
 This implementation does not support the Tree Hashing Mode.

   For unkeyed hashing, developers adapting BLAKE2 to ASN.1 - based
   message formats SHOULD use the OID tree at x = 1.3.6.1.4.1.1722.12.2.
         Algorithm     | Target | Collision | Hash | Hash ASN.1 |
            Identifier |  Arch  |  Security |  nn  | OID Suffix |
        ---------------+--------+-----------+------+------------+
         id-blake2b160 | 64-bit |   2**80   |  20  |   x.1.20   |
         id-blake2b256 | 64-bit |   2**128  |  32  |   x.1.32   |
         id-blake2b384 | 64-bit |   2**192  |  48  |   x.1.48   |
         id-blake2b512 | 64-bit |   2**256  |  64  |   x.1.64   |
        ---------------+--------+-----------+------+------------+
 */

import java.util.Arrays;

/**
 * Implementation of the cryptographic hash function Blake2b.
 * <p>
 * Blake2b offers a built-in keying mechanism to be used directly
 * for authentication ("Prefix-MAC") rather than a HMAC construction.
 * <p>
 * Blake2b offers a built-in support for a salt for randomized hashing
 * and a personal string for defining a unique hash function for each application.
 * <p>
 * BLAKE2b is optimized for 64-bit platforms and produces digests of any size
 * between 1 and 64 bytes.
 */
public final class Blake2b {
    // Blake2b Initialization Vector:
    private final static long[] blake2b_IV = {
            // Produced from the square root of primes 2, 3, 5, 7, 11, 13, 17, 19.
            // The same as SHA-512 IV.
            0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL,
            0xa54ff53a5f1d36f1L, 0x510e527fade682d1L, 0x9b05688c2b3e6c1fL,
            0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
    };

    // Message word permutations:
    private final static byte[][] blake2b_sigma = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3},
            {11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4},
            {7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8},
            {9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13},
            {2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9},
            {12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11},
            {13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10},
            {6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5},
            {10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}
    };
    private final static int BLOCK_LENGTH_BYTES = 128;// bytes
    private static final int ROUNDS = 12; // to use for Catenas H'
    // General parameters:
    private int digestLength = 64; // 1- 64 bytes
    private int keyLength = 0; // 0 - 64 bytes for keyed hashing for MAC
    private byte[] salt = null;// new byte[16];
    private byte[] personalization = null;// new byte[16];

    // the key
    private byte[] key = null;

    // Tree hashing parameters:
    // Because this class does not implement the Tree Hashing Mode,
    // these parameters can be treated as constants (see init() function)
    /*
     * private int fanout = 1; // 0-255 private int depth = 1; // 1 - 255
     * private int leafLength= 0; private long nodeOffset = 0L; private int
     * nodeDepth = 0; private int innerHashLength = 0;
     */

    // whenever this buffer overflows, it will be processed
    // in the compress() function.
    // For performance issues, long messages will not use this buffer.
    private byte[] buffer = null;// new byte[BLOCK_LENGTH_BYTES];
    // Position of last inserted byte:
    private int bufferPos = 0;// a value from 0 up to 128

    private long[] internalState = new long[16]; // In the Blake2b paper it is
    // called: v
    private long[] chainValue = null; // state vector, in the Blake2b paper it
    // is called: h

    private long t0 = 0L; // holds last significant bits, counter (counts bytes)
    private long t1 = 0L; // counter: Length up to 2^128 are supported
    private long f0 = 0L; // finalization flag, for last block: ~0L

    // For Tree Hashing Mode, not used here:
    // private long f1 = 0L; // finalization flag, for last node: ~0L

    public Blake2b() {
        this(512);
    }

    public Blake2b(Blake2b digest) {
        this.bufferPos = digest.bufferPos;
        this.buffer = clone(digest.buffer);
        this.keyLength = digest.keyLength;
        this.key = clone(digest.key);
        this.digestLength = digest.digestLength;
        this.chainValue = clone(digest.chainValue);
        this.personalization = clone(digest.personalization);
        this.salt = clone(digest.salt);
        this.t0 = digest.t0;
        this.t1 = digest.t1;
        this.f0 = digest.f0;
    }

    /**
     * Basic sized constructor - size in bits.
     *
     * @param digestSize size of the digest in bits
     */
    public Blake2b(int digestSize) {
        if (digestSize < 8 || digestSize > 512 || digestSize % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        buffer = new byte[BLOCK_LENGTH_BYTES];
        keyLength = 0;
        this.digestLength = digestSize / 8;
        init();
    }

    /**
     * Blake2b for authentication ("Prefix-MAC mode").
     * After calling the doFinal() method, the key will
     * remain to be used for further computations of
     * this instance.
     * The key can be overwritten using the clearKey() method.
     *
     * @param key A key up to 64 bytes or null
     */
    public Blake2b(byte[] key) {
        buffer = new byte[BLOCK_LENGTH_BYTES];
        if (key != null) {
            this.key = new byte[key.length];
            System.arraycopy(key, 0, this.key, 0, key.length);

            if (key.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            keyLength = key.length;
            System.arraycopy(key, 0, buffer, 0, key.length);
            bufferPos = BLOCK_LENGTH_BYTES; // zero padding
        }
        digestLength = 64;
        init();
    }

    /**
     * Blake2b with key, required digest length (in bytes), salt and personalization.
     * After calling the doFinal() method, the key, the salt and the personal string
     * will remain and might be used for further computations with this instance.
     * The key can be overwritten using the clearKey() method, the salt (pepper)
     * can be overwritten using the clearSalt() method.
     *
     * @param key             A key up to 64 bytes or null
     * @param digestLength    from 1 up to 64 bytes
     * @param salt            16 bytes or null
     * @param personalization 16 bytes or null
     */
    public Blake2b(byte[] key, int digestLength, byte[] salt, byte[] personalization) {
        buffer = new byte[BLOCK_LENGTH_BYTES];
        if (digestLength < 1 || digestLength > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = digestLength;
        if (salt != null) {
            if (salt.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            this.salt = new byte[16];
            System.arraycopy(salt, 0, this.salt, 0, salt.length);
        }
        if (personalization != null) {
            if (personalization.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            this.personalization = new byte[16];
            System.arraycopy(personalization, 0, this.personalization, 0, personalization.length);
        }
        if (key != null) {
            this.key = new byte[key.length];
            System.arraycopy(key, 0, this.key, 0, key.length);

            if (key.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            keyLength = key.length;
            System.arraycopy(key, 0, buffer, 0, key.length);
            bufferPos = BLOCK_LENGTH_BYTES; // zero padding
        }
        init();
    }

    // initialize chainValue
    private void init() {
        if (chainValue == null) {
            chainValue = new long[8];

            chainValue[0] = blake2b_IV[0]
                    ^ (digestLength | (keyLength << 8) | 0x1010000);
            // 0x1010000 = ((fanout << 16) | (depth << 24) | (leafLength << 32));
            // with fanout = 1; depth = 0; leafLength = 0;
            chainValue[1] = blake2b_IV[1];// ^ nodeOffset; with nodeOffset = 0;
            chainValue[2] = blake2b_IV[2];// ^ ( nodeDepth | (innerHashLength << 8));
            // with nodeDepth = 0; innerHashLength = 0;

            chainValue[3] = blake2b_IV[3];

            chainValue[4] = blake2b_IV[4];
            chainValue[5] = blake2b_IV[5];
            if (salt != null) {
                chainValue[4] ^= littleEndianToLong(salt, 0);
                chainValue[5] ^= littleEndianToLong(salt, 8);
            }

            chainValue[6] = blake2b_IV[6];
            chainValue[7] = blake2b_IV[7];
            if (personalization != null) {
                chainValue[6] ^= littleEndianToLong(personalization, 0);
                chainValue[7] ^= littleEndianToLong(personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        // initialize v:
        System.arraycopy(chainValue, 0, internalState, 0, chainValue.length);
        System.arraycopy(blake2b_IV, 0, internalState, chainValue.length, 4);
        internalState[12] = t0 ^ blake2b_IV[4];
        internalState[13] = t1 ^ blake2b_IV[5];
        internalState[14] = f0 ^ blake2b_IV[6];
        internalState[15] = blake2b_IV[7];// ^ f1 with f1 = 0
    }

    /**
     * update the message digest with a single byte.
     *
     * @param b the input byte to be entered.
     */
    public void update(byte b) {
        int remainingLength = 0; // left bytes of buffer

        // process the buffer if full else add to buffer:
        remainingLength = BLOCK_LENGTH_BYTES - bufferPos;
        if (remainingLength == 0) { // full buffer
            t0 += BLOCK_LENGTH_BYTES;
            if (t0 == 0) { // if message > 2^64
                t1++;
            }
            compress(buffer, 0);
            Arrays.fill(buffer, (byte) 0);// clear buffer
            buffer[0] = b;
            bufferPos = 1;
        } else {
            buffer[bufferPos] = b;
            bufferPos++;
            return;
        }
    }

    /**
     * update the message digest with a block of bytes.
     *
     * @param message the byte array containing the data.
     * @param offset  the offset into the byte array where the data starts.
     * @param len     the length of the data.
     */
    public void update(byte[] message, int offset, int len) {
        if (message == null || len == 0) {
            return;
        }
        int remainingLength = 0; // left bytes of buffer

        if (bufferPos != 0) { // commenced, incomplete buffer
            // complete the buffer:
            remainingLength = BLOCK_LENGTH_BYTES - bufferPos;
            if (remainingLength < len) { // full buffer + at least 1 byte
                System.arraycopy(message, offset, buffer, bufferPos, remainingLength);
                t0 += BLOCK_LENGTH_BYTES;
                if (t0 == 0) { // if message > 2^64
                    t1++;
                }
                compress(buffer, 0);
                bufferPos = 0;
                Arrays.fill(buffer, (byte) 0);// clear buffer
            } else {
                System.arraycopy(message, offset, buffer, bufferPos, len);
                bufferPos += len;
                return;
            }
        }

        // process blocks except last block (also if last block is full)
        int messagePos;
        int blockWiseLastPos = offset + len - BLOCK_LENGTH_BYTES;
        for (messagePos = offset + remainingLength; messagePos < blockWiseLastPos; messagePos += BLOCK_LENGTH_BYTES) { // block wise 128 bytes
            // without buffer:
            t0 += BLOCK_LENGTH_BYTES;
            if (t0 == 0) {
                t1++;
            }
            compress(message, messagePos);
        }

        // fill the buffer with left bytes, this might be a full block
        System.arraycopy(message, messagePos, buffer, 0, offset + len - messagePos);
        bufferPos += offset + len - messagePos;
    }

    /**
     * close the digest, producing the final digest value. The doFinal
     * call leaves the digest reset.
     * Key, salt and personal string remain.
     *
     * @param out       the array the digest is to be copied into.
     * @param outOffset the offset into the out array the digest is to start at.
     */
    public int doFinal(byte[] out, int outOffset) {
        f0 = 0xFFFFFFFFFFFFFFFFL;
        t0 += bufferPos;
        if (bufferPos > 0 && t0 == 0) {
            t1++;
        }
        compress(buffer, 0);
        Arrays.fill(buffer, (byte) 0);// Holds eventually the key if input is null
        Arrays.fill(internalState, 0L);

        for (int i = 0; i < chainValue.length && (i * 8 < digestLength); i++) {
            byte[] bytes = longToLittleEndian(chainValue[i]);
            if (i * 8 < digestLength - 8) {
                System.arraycopy(bytes, 0, out, outOffset + i * 8, 8);
            } else {
                System.arraycopy(bytes, 0, out, outOffset + i * 8, digestLength - (i * 8));
            }
        }
        Arrays.fill(chainValue, 0L);
        reset();
        return digestLength;
    }

    /**
     * Reset the digest back to it's initial state.
     * The key, the salt and the personal string will
     * remain for further computations.
     */
    public void reset() {
        bufferPos = 0;
        f0 = 0L;
        t0 = 0L;
        t1 = 0L;
        chainValue = null;
        Arrays.fill(buffer, (byte) 0);
        if (key != null) {
            System.arraycopy(key, 0, buffer, 0, key.length);
            bufferPos = BLOCK_LENGTH_BYTES; // zero padding
        }
        init();
    }

    private void compress(byte[] message, int messagePos) {

        initializeInternalState();

        long[] m = new long[16];
        for (int j = 0; j < 16; j++) {
            m[j] = littleEndianToLong(message, messagePos + j * 8);
        }

        for (int round = 0; round < ROUNDS; round++) {

            // G apply to columns of internalState:m[blake2b_sigma[round][2 *
            // blockPos]] /+1
            G(m[blake2b_sigma[round][0]], m[blake2b_sigma[round][1]], 0, 4, 8, 12);
            G(m[blake2b_sigma[round][2]], m[blake2b_sigma[round][3]], 1, 5, 9, 13);
            G(m[blake2b_sigma[round][4]], m[blake2b_sigma[round][5]], 2, 6, 10, 14);
            G(m[blake2b_sigma[round][6]], m[blake2b_sigma[round][7]], 3, 7, 11, 15);
            // G apply to diagonals of internalState:
            G(m[blake2b_sigma[round][8]], m[blake2b_sigma[round][9]], 0, 5, 10, 15);
            G(m[blake2b_sigma[round][10]], m[blake2b_sigma[round][11]], 1, 6, 11, 12);
            G(m[blake2b_sigma[round][12]], m[blake2b_sigma[round][13]], 2, 7, 8, 13);
            G(m[blake2b_sigma[round][14]], m[blake2b_sigma[round][15]], 3, 4, 9, 14);
        }

        // update chain values:
        for (int offset = 0; offset < chainValue.length; offset++) {
            chainValue[offset] = chainValue[offset] ^ internalState[offset] ^ internalState[offset + 8];
        }
    }

    private void G(long m1, long m2, int posA, int posB, int posC, int posD) {
        internalState[posA] = internalState[posA] + internalState[posB] + m1;
        internalState[posD] = rotateRight(internalState[posD] ^ internalState[posA], 32);
        internalState[posC] = internalState[posC] + internalState[posD];
        internalState[posB] = rotateRight(internalState[posB] ^ internalState[posC], 24); // replaces 25 of BLAKE
        internalState[posA] = internalState[posA] + internalState[posB] + m2;
        internalState[posD] = rotateRight(internalState[posD] ^ internalState[posA], 16);
        internalState[posC] = internalState[posC] + internalState[posD];
        internalState[posB] = rotateRight(internalState[posB] ^ internalState[posC], 63); // replaces 11 of BLAKE
    }

    /**
     * return the algorithm name
     *
     * @return the algorithm name
     */
    public String getAlgorithmName() {
        return "BLAKE2b";
    }

    /**
     * return the size, in bytes, of the digest produced by this message digest.
     *
     * @return the size, in bytes, of the digest produced by this message digest.
     */
    public int getDigestSize() {
        return digestLength;
    }

    /**
     * Return the size in bytes of the internal buffer the digest applies it's compression
     * function to.
     *
     * @return byte length of the digests internal buffer.
     */
    public int getByteLength() {
        return BLOCK_LENGTH_BYTES;
    }

    /**
     * Overwrite the key
     * if it is no longer used (zeroization)
     */
    public void clearKey() {
        if (key != null) {
            Arrays.fill(key, (byte) 0);
            Arrays.fill(buffer, (byte) 0);
        }
    }

    /**
     * Overwrite the salt (pepper) if it
     * is secret and no longer used (zeroization)
     */
    public void clearSalt() {
        if (salt != null) {
            Arrays.fill(salt, (byte) 0);
        }
    }

    // factory methods

    public static Blake2b blake2b512() {
        return new Blake2b();
    }

    public static Blake2b blake2b384() {
        return new Blake2b(384);
    }

    public static Blake2b blake2b256() {
        return new Blake2b(256);
    }

    public static Blake2b blake2b160() {
        return new Blake2b(160);
    }


    // utility methods

    private static byte[] clone(byte[] arr) {
        return (arr == null) ? null : arr.clone();
    }

    private static long[] clone(long[] data) {
        return null == data ? null : data.clone();
    }

    private static int littleEndianToInt(byte[] bs, int off) {
        int n = bs[off] & 0xff;
        n |= (bs[++off] & 0xff) << 8;
        n |= (bs[++off] & 0xff) << 16;
        n |= bs[++off] << 24;
        return n;
    }

    private static void intToLittleEndian(int n, byte[] bs, int off) {
        bs[off] = (byte) (n);
        bs[++off] = (byte) (n >>> 8);
        bs[++off] = (byte) (n >>> 16);
        bs[++off] = (byte) (n >>> 24);
    }

    private static long littleEndianToLong(byte[] bs, int off) {
        int lo = littleEndianToInt(bs, off);
        int hi = littleEndianToInt(bs, off + 4);
        return ((long) (hi & 0xffffffffL) << 32) | (long) (lo & 0xffffffffL);
    }

    private static byte[] longToLittleEndian(long n) {
        byte[] bs = new byte[8];
        longToLittleEndian(n, bs, 0);
        return bs;
    }

    private static void longToLittleEndian(long n, byte[] bs, int off) {
        intToLittleEndian((int) (n & 0xffffffffL), bs, off);
        intToLittleEndian((int) (n >>> 32), bs, off + 4);
    }

    private static long rotateRight(long i, int distance) {
        return Long.rotateRight(i, distance);
    }
}