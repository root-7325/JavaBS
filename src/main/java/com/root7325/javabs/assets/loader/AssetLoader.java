package com.root7325.javabs.assets.loader;

import com.root7325.javabs.assets.model.Asset;

import java.util.List;

/**
 * @author root7325 on 28.06.2025
 */
public interface AssetLoader<T extends Asset> {
    List<T> load(String filePath);
}
