package com.root7325.javabs.dao;

import com.root7325.javabs.entity.player.Player;

/**
 * @author root7325 on 17.06.2025
 */
public interface PlayerDAO {
    Player getPlayer(int id);
    Player getPlayer(int id, String token);
    Player createPlayer();
}