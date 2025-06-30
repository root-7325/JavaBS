package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Card implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private String target;
    @CsvBindByName
    private int metaType;
    @CsvBindByName
    private String requiresCard;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String skill;
    @CsvBindByName
    private int value;
    @CsvBindByName
    private int value2;
    @CsvBindByName
    private String rarity;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private String powerNumberTID;
    @CsvBindByName
    private String powerNumber2TID;
    @CsvBindByName
    private String powerIcon1ExportName;
    @CsvBindByName
    private String powerIcon2ExportName;
    @CsvBindByName
    private int sortOrder;
} 