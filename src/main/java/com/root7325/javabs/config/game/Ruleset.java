package com.root7325.javabs.config.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

/**
 * @author root7325 on 25.06.2025
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ruleset {
    private ShopSettings shopSettings;
    private int tokensCap;
    private int tokensRechargeAmount;
    private int tokensRechargePeriod;
    private int boxTokensCap;
    private int[] brawlerUpgradeCost;
    private int minimumTrophiesForReset;
    private int defaultTutorialStep;
}
