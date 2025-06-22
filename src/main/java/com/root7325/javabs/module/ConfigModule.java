package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.root7325.javabs.config.Config;
import com.root7325.javabs.config.CryptoConfig;
import com.root7325.javabs.config.ServerConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author root7325 on 21.06.2025
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.class).toProvider(ConfigProvider.class).in(Singleton.class);
    }

    @Provides
    private ServerConfig provideServerConfig(Config config) {
        return config.getServerConfig();
    }

    @Provides
    private CryptoConfig provideCryptoConfig(Config config) {
        return config.getCryptoConfig();
    }
}

class ConfigProvider implements Provider<Config> {
    private static final String YAML_FILE = "config.yaml";

    @Override
    public Config get() {
        Yaml yaml = new Yaml();

        try (InputStream inputStream = Config.class.getClassLoader()
                .getResourceAsStream(YAML_FILE)) {
            return yaml.loadAs(inputStream, Config.class);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load yaml configuration", ex);
        }
    }
}