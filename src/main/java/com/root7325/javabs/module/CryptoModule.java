package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.root7325.javabs.laser.crypto.ICrypto;
import com.root7325.javabs.laser.crypto.PepperCrypto;

/**
 * @author root7325 on 22.06.2025
 */
public class CryptoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ICrypto.class).to(PepperCrypto.class);
    }
}
