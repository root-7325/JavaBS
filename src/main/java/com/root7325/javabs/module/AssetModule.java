package com.root7325.javabs.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.root7325.javabs.assets.loader.AssetLoader;
import com.root7325.javabs.assets.loader.CsvAssetLoader;
import com.root7325.javabs.assets.manager.location.LocationManager;
import com.root7325.javabs.assets.manager.location.LocationManagerImpl;
import com.root7325.javabs.assets.manager.thumbnail.ThumbnailManager;
import com.root7325.javabs.assets.manager.thumbnail.ThumbnailManagerImpl;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.assets.model.PlayerThumbnail;

/**
 * @author root7325 on 28.06.2025
 */
public class AssetModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ThumbnailManager.class).to(ThumbnailManagerImpl.class).asEagerSingleton();
        bind(LocationManager.class).to(LocationManagerImpl.class).asEagerSingleton();
    }

    @Singleton
    @Provides
    private AssetLoader<PlayerThumbnail> provideThumbnailLoader() {
        return new CsvAssetLoader<>(PlayerThumbnail.class);
    }

    @Singleton
    @Provides
    private AssetLoader<Location> provideLocationLoader() {
        return new CsvAssetLoader<>(Location.class);
    }
}
