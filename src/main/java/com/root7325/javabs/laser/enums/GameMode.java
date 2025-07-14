package com.root7325.javabs.laser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * Enumeration of all game modes.
 *
 * @author root7325 on 30.06.2025
 */
@Getter
@AllArgsConstructor
public enum GameMode {
    CoinRush(Duration.ofHours(6)),
    BountyHunter(Duration.ofHours(7)),
    LaserBall(Duration.ofHours(7)),
    AttackDefend(Duration.ofHours(10)),
    BattleRoyale(Duration.ofHours(10)),
    BattleRoyaleTeam, // this mode (and slot) uses solo mode time
    Survival(Duration.ofDays(2)),
    Tutorial,
    BossFight(Duration.ofDays(2));

    GameMode() {
        this.duration = Duration.ofMinutes(30);
    }

    private final Duration duration;
}
