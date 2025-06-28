package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class AllianceBadge implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private String category;
} 