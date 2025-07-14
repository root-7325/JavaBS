package com.root7325.javabs.assets.manager.location;

import com.root7325.javabs.assets.manager.core.AssetManager;
import com.root7325.javabs.assets.model.Location;
import com.root7325.javabs.laser.enums.GameMode;

import java.util.List;
import java.util.Optional;

/**
 * Interface for managing location assets from {@code locations.csv}.
 *
 * @author root7325 on 28.06.2025
 */
public interface LocationManager extends AssetManager<Location> {
    /**
     * Retrieves all locations for a specific game mode.
     *
     * @param gameMode game mode to filter by
     * @return list of locations matching the game mode
     */
    List<Location> getByGameMode(GameMode gameMode);

    /**
     * Retrieves a random location for a specific game mode.
     *
     * @param gameMode game mode to filter by
     * @return optional containing a randomly selected location
     */
    Optional<Location> getRandomByGameMode(GameMode gameMode);

    /**
     * Retrieves team pair location for BattleRoyaleTeam mode based on a BattleRoyale solo map ID.
     *
     * @param id ID of the solo BattleRoyale map
     * @return optional containing corresponding team location
     */
    Optional<Location> getTeamPairById(int id);
}
