package com.root7325.javabs.config.server;

/**
 * @author root7325 on 17.06.2025
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author root7325 on 10.02.2025
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Config {
    private ServerConfig serverConfig;
    private CryptoConfig cryptoConfig;
}
