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
    private static final String YAML_FILE = "config.yaml";

    private static Config instance;

    private ServerConfig serverConfig;
    private CryptoConfig cryptoConfig;

    public static Config getInstance() {
        if (instance == null) {
            instance = loadConfig();
        }
        return instance;
    }

    private static Config loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(YAML_FILE)) {
            return yaml.loadAs(inputStream, Config.class);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load yaml configuration.", ex);
        }
    }
}
