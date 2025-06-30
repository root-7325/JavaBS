package com.root7325.javabs.dao;

import com.root7325.javabs.entity.player.Player;

import java.util.concurrent.CompletableFuture;

/**
 * @author root7325 on 17.06.2025
 */
public interface PlayerDAO {
    CompletableFuture<Player> getPlayer(long id);
    CompletableFuture<Player> getPlayer(long id, String token);
    CompletableFuture<Player> createPlayer();
    CompletableFuture<Player> savePlayer(Player player);
}