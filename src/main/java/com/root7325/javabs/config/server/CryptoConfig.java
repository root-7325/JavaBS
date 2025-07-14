package com.root7325.javabs.config.server;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration class for cryptographic settings used in client-server communication.
 *
 * @author root7325 on 17.06.2025
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoConfig {
    private String serverKey;
    private String clientSecretKey;
}
