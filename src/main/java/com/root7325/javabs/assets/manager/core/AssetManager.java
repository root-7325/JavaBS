package com.root7325.javabs.assets.manager.core;

import com.root7325.javabs.assets.model.Asset;

import java.util.List;

/**
 * Interface for managing game assets of a specific type.
 *
 * @author root7325 on 28.06.2025
 */
public interface AssetManager<T extends Asset> {
    /**
     * Retrieves all assets of managed type.
     *
     * @return List containing all assets
     */
    List<T> getAll();
}
