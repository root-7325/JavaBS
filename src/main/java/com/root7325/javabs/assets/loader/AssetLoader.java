package com.root7325.javabs.assets.loader;

import com.root7325.javabs.assets.model.Asset;

import java.util.List;

/**
 * Generic interface for loading assets from files.
 *
 * @param <T> type of game asset to be loaded, must extend Asset class
 * @author root7325 on 28.06.2025
 */
public interface AssetLoader<T extends Asset> {

    /**
     * Loads assets from the specified file path.
     *
     * @param filePath path to file containing asset data
     * @return a list of loaded Asset objects of type T
     */
    List<T> load(String filePath);
}
