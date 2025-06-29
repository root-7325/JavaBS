package com.root7325.javabs.config.server;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author root7325 on 17.06.2025
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerConfig {
    private String host;
    private int port;
    private String assetsPath;
}
