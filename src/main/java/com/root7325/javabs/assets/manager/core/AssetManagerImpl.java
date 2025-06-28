package com.root7325.javabs.assets.manager.core;

import com.google.inject.Inject;
import com.root7325.javabs.assets.loader.AssetLoader;
import com.root7325.javabs.assets.model.Asset;
import com.root7325.javabs.assets.model.AssetType;
import com.root7325.javabs.config.server.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author root7325 on 28.06.2025
 */
@Slf4j
public class AssetManagerImpl<T extends Asset> implements AssetManager<T> {
    private final AssetType assetType;
    private final List<T> list;

    @Inject
    public AssetManagerImpl(AssetType assetType, ServerConfig serverConfig, AssetLoader<T> assetLoader) {
        this.assetType = assetType;
        this.list = new ArrayList<>();

        Path assetPath = Paths.get(serverConfig.getAssetsPath(), assetType.getRelativePath());
        assetLoader.load(assetPath.toString());
    }

    @Override
    public List<T> getAll() {
        return List.copyOf(list);
    }
}
