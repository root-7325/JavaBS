package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.root7325.javabs.assets.loader.AssetLoader;
import com.root7325.javabs.assets.loader.CsvAssetLoader;
import com.root7325.javabs.assets.manager.thumbnail.ThumbnailManager;
import com.root7325.javabs.assets.manager.thumbnail.ThumbnailManagerImpl;
import com.root7325.javabs.assets.model.PlayerThumbnails;

/**
 * @author root7325 on 28.06.2025
 */
public class AssetModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ThumbnailManager.class).to(ThumbnailManagerImpl.class).asEagerSingleton();
    }

    @Provides
    private AssetLoader<PlayerThumbnails> provideThumbnailLoader() {
        return new CsvAssetLoader<>(PlayerThumbnails.class);
    }
}
