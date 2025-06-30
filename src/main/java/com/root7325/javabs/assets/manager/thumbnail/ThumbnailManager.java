package com.root7325.javabs.assets.manager.thumbnail;

import com.root7325.javabs.assets.manager.core.AssetManager;
import com.root7325.javabs.assets.model.PlayerThumbnail;

import java.util.Optional;

/**
 * @author root7325 on 28.06.2025
 */
public interface ThumbnailManager extends AssetManager<PlayerThumbnail> {
    Optional<PlayerThumbnail> getThumbnail(int id);
}
