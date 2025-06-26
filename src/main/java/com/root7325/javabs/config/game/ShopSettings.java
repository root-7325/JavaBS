package com.root7325.javabs.config.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author root7325 on 26.06.2025
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopSettings {
    private int brawlBoxTokenCost;
    private int brawlBoxShopCost;
    private int bigBoxShopCost;
    private int megaBoxShopCost;
    private int bigBoxTokenCost;
    private int tokenDoublerCost;
    private int tokenDoublerAmount;
    private int[] eventTicketsCost;
    private int[] eventTicketsValue;
    private int[] coinPacksCost;
    private int[] coinPacksValue;
}
