package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Campaign implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private String location;
    @CsvBindByName
    private String allowedHeroes;
    @CsvBindByName
    private String reward;
    @CsvBindByName
    private int levelGenerationSeed;
    @CsvBindByName
    private String map;
    @CsvBindByName
    private String enemies;
    @CsvBindByName
    private int enemyLevel;
    @CsvBindByName
    private String boss;
    @CsvBindByName
    private int bossLevel;
    @CsvBindByName
    private String base;
    @CsvBindByName
    private int numBases;
    @CsvBindByName
    private int baseLevel;
    @CsvBindByName
    private String tower;
    @CsvBindByName
    private int numTowers;
    @CsvBindByName
    private int towerLevel;
    @CsvBindByName
    private int requiredStars;
} 