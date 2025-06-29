package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Item implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String fileName;
    @CsvBindByName
    private String exportName;
    @CsvBindByName
    private String exportNameEnemy;
    @CsvBindByName
    private String shadowExportName;
    @CsvBindByName
    private String groundGlowExportName;
    @CsvBindByName
    private String loopingEffect;
    @CsvBindByName
    private int value;
    @CsvBindByName
    private int value2;
    @CsvBindByName
    private int triggerRangeSubTiles;
    @CsvBindByName
    private String triggerAreaEffect;
    @CsvBindByName
    private boolean canBePickedUp;
    @CsvBindByName
    private String spawnEffect;
} 