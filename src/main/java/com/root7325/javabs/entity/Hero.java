package com.root7325.javabs.entity;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.logic.commons.GlobalId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author root7325 on 19.06.2025
 */
@Data
@Entity
@NoArgsConstructor
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hero_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "card_id")
    private int cardId;

    @Column(name = "char_id")
    private int charId;

    private int score = 0;

    @Column(name = "highest_score")
    private int highestScore = 0;

    @Column(name = "upgradium_points")
    private int upgradiumPoints = 0;

    @Column(name = "power_level")
    private int powerLevel = 8;

    @Column(name = "seen")
    private boolean seen = false;

    public Hero(Player player, int cardId, int charId) {
        this.player = player;
        this.cardId = cardId;
        this.charId = charId;
    }

    public GlobalId getCard() {
        return new GlobalId(23, cardId);
    }

    public GlobalId getChar() {
        return new GlobalId(16, charId);
    }
}
