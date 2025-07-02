package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.root7325.javabs.config.game.Ruleset;
import com.root7325.javabs.config.server.Config;
import com.root7325.javabs.config.server.CryptoConfig;
import com.root7325.javabs.config.server.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author root7325 on 21.06.2025
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.class).toProvider(ConfigProvider.class).asEagerSingleton();
        bind(Ruleset.class).toProvider(RulesetProvider.class).asEagerSingleton();
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

@Slf4j
class RulesetProvider implements Provider<Ruleset> {
    private static final String YAML_FILE = "ruleset.yaml";

    @Override
    public Ruleset get() {
        Yaml yaml = new Yaml();

        try {
            log.debug("Trying to load external ruleset file: {}", YAML_FILE);
            return loadExternal(yaml);
        } catch (IOException ex) {
            log.debug("Falling back to internal ruleset resource: {}", YAML_FILE);
            return loadInternal(yaml);
        }
    }

    private Ruleset loadExternal(Yaml yaml) throws IOException {
        try (InputStream external = new FileInputStream(YAML_FILE)) {
            return yaml.loadAs(external, Ruleset.class);
        }
    }

    private Ruleset loadInternal(Yaml yaml) {
        try (InputStream inputStream = Config.class.getClassLoader()
                .getResourceAsStream(YAML_FILE)) {
            return yaml.loadAs(inputStream, Ruleset.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

@Slf4j
class ConfigProvider implements Provider<Config> {
    private static final String YAML_FILE = "config.yaml";

    @Override
    public Config get() {
        Yaml yaml = new Yaml();

        try {
            log.debug("Trying to load external config file: {}", YAML_FILE);
            return loadExternal(yaml);
        } catch (IOException ex) {
            log.debug("Falling back to internal config resource: {}", YAML_FILE);
            return loadInternal(yaml);
        }
    }

    private Config loadExternal(Yaml yaml) throws IOException {
        try (InputStream external = new FileInputStream(YAML_FILE)) {
            return yaml.loadAs(external, Config.class);
        }
    }

    private Config loadInternal(Yaml yaml) {
        try (InputStream inputStream = Config.class.getClassLoader()
                .getResourceAsStream(YAML_FILE)) {
            return yaml.loadAs(inputStream, Config.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}