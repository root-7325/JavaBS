package com.root7325.javabs.entity.player;

import com.root7325.javabs.laser.logic.commons.GlobalId;
import com.root7325.javabs.laser.logic.commons.LogicDataSlot;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author root7325 on 22.06.2025
 */
@Data
@Embeddable
public class PlayerResources {
    @Column(name = "trophy_road_progress")
    private int trophyRoadProgress = 1;

    @Column(name = "coins")
    private int coinsAmount = 1000;

    @Transient
    private LogicDataSlot coins;

    @Column(name = "brawl_box_tokens")
    private int brawlBoxTokensAmount;

    @Transient
    private LogicDataSlot brawlBoxTokens;

    @Column(name = "big_box_tokens")
    private int bigBoxTokensAmount;

    @Transient
    private LogicDataSlot bigBoxTokens;

    private int tickets;

    private int diamonds = 30;

    private int exp;

    public PlayerResources() {
        initializeResourceSlots();
    }

    @PostLoad
    void postLoad() {
        initializeResourceSlots();
    }

    private void initializeResourceSlots() {
        this.setCoins(new LogicDataSlot(new GlobalId(5, 8), coinsAmount));
        this.setBrawlBoxTokens(new LogicDataSlot(new GlobalId(5, 1), brawlBoxTokensAmount));
        this.setBigBoxTokens(new LogicDataSlot(new GlobalId(5, 9), bigBoxTokensAmount));
    }

    public boolean useDiamonds(int i) {
        if ((diamonds - i) >= 0) {
            diamonds -= i;
            return true;
        }
        return false;
    }
}
