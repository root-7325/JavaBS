package com.root7325.javabs.entity.player;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author root7325 on 22.06.2025
 */
@Data
@Embeddable
public class PlayerStats {
    public int score = 0;

    @Column(name = "highest_score")
    public int highestScore = 0;

    @Column(name = "trio_wins")
    public int trioWins = 0;

    @Column(name = "duo_wins")
    public int duoWins = 0;

    @Column(name = "solo_wins")
    public int soloWins = 0;
}
