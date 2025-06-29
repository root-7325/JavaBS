package com.root7325.javabs.assets.manager.thumbnail;

import com.google.inject.Inject;
import com.root7325.javabs.assets.loader.AssetLoader;
import com.root7325.javabs.assets.manager.core.AssetManagerImpl;
import com.root7325.javabs.assets.model.AssetType;
import com.root7325.javabs.assets.model.PlayerThumbnail;
import com.root7325.javabs.config.server.ServerConfig;

/**
 * @author root7325 on 28.06.2025
 */
public class ThumbnailManagerImpl extends AssetManagerImpl<PlayerThumbnail> implements ThumbnailManager {
    @Inject
    public ThumbnailManagerImpl(ServerConfig serverConfig, AssetLoader<PlayerThumbnail> assetLoader) {
        super(AssetType.PlayerThumbnails, serverConfig, assetLoader);
    }
}
