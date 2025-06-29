package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Location implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private boolean disabled;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private String tileSetPrefix;
    @CsvBindByName
    private String bgPrefix;
    @CsvBindByName
    private String swf;
    @CsvBindByName
    private int tileSetRedAdd;
    @CsvBindByName
    private int tileSetGreenAdd;
    @CsvBindByName
    private int tileSetBlueAdd;
    @CsvBindByName
    private int tileSetRedMul;
    @CsvBindByName
    private int tileSetGreenMul;
    @CsvBindByName
    private int tileSetBlueMul;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private String gameMode;
    @CsvBindByName
    private String allowedMaps;
    @CsvBindByName
    private int shadowR;
    @CsvBindByName
    private int shadowG;
    @CsvBindByName
    private int shadowB;
    @CsvBindByName
    private int shadowA;
    @CsvBindByName
    private String music;
    @CsvBindByName
    private boolean largeMap;
    @CsvBindByName
    private String communityCredit;
} 