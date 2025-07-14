package com.root7325.javabs.assets.model;

import com.root7325.javabs.assets.loader.CsvAssetLoader;

/**
 * Base interface for all game assets.
 *
 * @author root7325 on 28.06.2025
 */
public interface Asset {
    /**
     * Sets ID to this asset object.
     * See {@link CsvAssetLoader} for explanation.
     */
    void setId(int id);
}
