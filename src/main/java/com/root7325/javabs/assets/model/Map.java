package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Map implements Asset {
    private int id;
    @CsvBindByName
    private String codeName;
    @CsvBindByName
    private String group;
    @CsvBindByName
    private String data;
    @CsvBindByName
    private boolean constructFromBlocks;
} 