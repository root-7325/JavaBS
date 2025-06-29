package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Resource implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String collectEffect;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String rarity;
    @CsvBindByName
    private boolean premiumCurrency;
    @CsvBindByName
    private int textRed;
    @CsvBindByName
    private int textGreen;
    @CsvBindByName
    private int textBlue;
    @CsvBindByName
    private int cap;
} 