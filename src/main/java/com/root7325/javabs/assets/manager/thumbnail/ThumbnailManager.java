package com.root7325.javabs.assets.manager.thumbnail;

import com.root7325.javabs.assets.manager.core.AssetManager;
import com.root7325.javabs.assets.model.PlayerThumbnail;

import java.util.Optional;

/**
 * Interface for managing thumbnails assets from {@code player_thumbnails.csv}.
 *
 * @author root7325 on 28.06.2025
 */
public interface ThumbnailManager extends AssetManager<PlayerThumbnail> {
    /**
     * Retrieves thumbnail object by given id
     * @param id ID of required thumbnail
     * @return optional containing corresponding thumbnail
     */
    Optional<PlayerThumbnail> getThumbnail(int id);
}
