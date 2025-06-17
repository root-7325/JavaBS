package com.root7325.javabs.utils.libraries;

public class Nonce {
    private byte[] nonce;
    public Nonce(byte[] cpk, byte[] spk) {
        Blake2b b2b = new Blake2b(192);
        b2b.update(cpk, 0, 32);
        b2b.update(spk, 0, 32);
        nonce = new byte[24];
        b2b.doFinal(nonce, 0);
    }
    public Nonce(byte[] nonce, byte[] cpk, byte[] spk) {
        Blake2b b2b = new Blake2b(192);
        b2b.update(nonce, 0, 24);
        b2b.update(cpk, 0, 32);
        b2b.update(spk, 0, 32);
        this.nonce = new byte[24];
        b2b.doFinal(this.nonce, 0);
    }
    public Nonce() {
        nonce = new byte[24];
        TweetNacl.randombytes(nonce, 24);
    }
    public Nonce(byte[] nonce) {
        this.nonce = nonce;
    }
    public byte[] bytes() {
        return nonce;
    }
    public void increment() {
        int c = 1;
        for (int idx = 0; idx < 47; idx++) {
            if (idx == 24) c = 1;
            c += (int)nonce[idx % 24] & 0xFF;
            nonce[idx % 24] = (byte)c;
            c >>= 8;
        }
    }
}