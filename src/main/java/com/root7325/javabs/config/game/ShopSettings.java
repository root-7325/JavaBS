package com.root7325.javabs.config.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Configuration class for in-game shop system and item pricing.
 *
 * @author root7325 on 26.06.2025
 */
@Getter
@Setter
public class ShopSettings {
    private int brawlBoxTokenCost;
    private int brawlBoxShopCost;
    private int bigBoxShopCost;
    private int megaBoxShopCost;
    private int bigBoxTokenCost;
    private int tokenDoublerCost;
    private int tokenDoublerAmount;
    private List<Integer> eventTicketsCost;
    private List<Integer> eventTicketsValue;
    private List<Integer> coinPacksCost;
    private List<Integer> coinPacksValue;
}
