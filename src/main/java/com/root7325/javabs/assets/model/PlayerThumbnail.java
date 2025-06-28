package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class PlayerThumbnail implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private int requiredExpLevel;
    @CsvBindByName
    private int requiredTotalTrophies;
    @CsvBindByName
    private int requiredSeasonPoints;
    @CsvBindByName
    private String requiredHero;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private int sortOrder;
}
