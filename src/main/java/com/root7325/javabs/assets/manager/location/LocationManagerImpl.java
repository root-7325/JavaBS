package com.root7325.javabs.assets.manager.location;

import com.google.inject.Inject;
import com.root7325.javabs.assets.loader.AssetLoader;
import com.root7325.javabs.assets.manager.core.AssetManagerImpl;
import com.root7325.javabs.assets.model.AssetType;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.config.server.ServerConfig;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author root7325 on 28.06.2025
 */
public class LocationManagerImpl extends AssetManagerImpl<Location> implements LocationManager {
    @Inject
    public LocationManagerImpl(ServerConfig serverConfig, AssetLoader<Location> assetLoader) {
        super(AssetType.Locations, serverConfig, assetLoader);
    }

    @Override
    public List<Location> getByGameMode(String gameMode) {
        return list.stream()
                .filter(location -> !location.isDisabled() && location.getGameMode().equals(gameMode)) // TODO: make configurable?
                .toList();
    }

    @Override
    public Optional<Location> getRandomByGameMode(String gameMode) {
        List<Location> filtered = list.stream()
            .filter(location -> !location.isDisabled() && location.getGameMode().equals(gameMode))
            .toList();
        if (filtered.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        return Optional.of(
                filtered.get(random.nextInt(filtered.size()))
        );
    }
}
