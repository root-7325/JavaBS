package com.root7325.javabs.config.server;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration class for basic server network and game assets settings.
 *
 * @author root7325 on 17.06.2025
 */
@Getter
@Setter
public class ServerConfig {
    private String host;
    private int port;
    private String assetsPath;
}
