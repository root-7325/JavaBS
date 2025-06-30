package com.root7325.javabs.assets.manager.location;

import com.root7325.javabs.assets.manager.core.AssetManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.GameMode;

import java.util.List;
import java.util.Optional;

/**
 * @author root7325 on 28.06.2025
 */
public interface LocationManager extends AssetManager<Location> {
    List<Location> getByGameMode(GameMode gameMode);
    Optional<Location> getRandomByGameMode(GameMode gameMode);
}
