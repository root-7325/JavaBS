package com.root7325.javabs.config.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author root7325 on 25.06.2025
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ruleset {
    private int tokensCap;
    private int tokensRechargeAmount;
    private int tokensRechargePeriod;
    private int[] brawlerUpgradeCost;
    private int[] coinPacksCost;
    private int[] coinPacksValue;
    private int brawlBoxTokenCost;
    private int bigBoxTokenCost;
    private int tokenDoublerCost;
    private int tokenDoublerAmount;
    private int minimumTrophiesForReset;
    private int defaultTutorialStep;
}
