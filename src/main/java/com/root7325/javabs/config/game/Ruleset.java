package com.root7325.javabs.config.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Configuration class that defines core game rules and settings.
 *
 * @author root7325 on 25.06.2025
 */
@Getter
@Setter
public class Ruleset {
    private ShopSettings shopSettings;
    private int tokensCap;
    private int tokensRechargeAmount;
    private int tokensRechargePeriod;
    private int boxTokensCap;
    private List<Integer> brawlerUpgradeCost;
    private int minimumTrophiesForReset;
    private int defaultTutorialStep;
}
