package com.root7325.javabs.dao;

import com.root7325.javabs.entity.player.Player;

import java.util.concurrent.CompletableFuture;

/**
 * Data Access Object interface for Player entity operations.
 *
 * @author root7325 on 17.06.2025
 */
public interface PlayerDAO {
    /**
     * Retrieves a player by their ID.
     *
     * @param id player's unique ID
     * @return a CompletableFuture containing the player, or null if not found
     */
    CompletableFuture<Player> getPlayer(long id);

    /**
     * Retrieves a player by ID and authentication token.
     *
     * @param id player's unique ID
     * @param token player's authentication token
     * @return a CompletableFuture containing player if token is valid, or null if not found/invalid
     */
    CompletableFuture<Player> getPlayer(long id, String token);

    /**
     * Creates a new player with default settings.
     *
     * @return a CompletableFuture containing the newly created player
     */
    CompletableFuture<Player> createPlayer();

    /**
     * Persists player data to database.
     *
     * @param player player object to save
     * @return a CompletableFuture containing saved player
     */
    CompletableFuture<Player> savePlayer(Player player);
}