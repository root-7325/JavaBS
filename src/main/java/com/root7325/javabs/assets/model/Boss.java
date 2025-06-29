package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Boss implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private int playerCount;
    @CsvBindByName
    private int requiredCampaignProgressToUnlock;
    @CsvBindByName
    private String location;
    @CsvBindByName
    private String allowedHeroes;
    @CsvBindByName
    private String reward;
    @CsvBindByName
    private String levelGenerationSeed;
    @CsvBindByName
    private String map;
    @CsvBindByName
    private String boss;
    @CsvBindByName
    private int bossLevel;
} 