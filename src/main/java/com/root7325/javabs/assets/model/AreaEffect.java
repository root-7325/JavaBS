package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class AreaEffect implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String fileName;
    @CsvBindByName
    private String blueExportName;
    @CsvBindByName
    private String redExportName;
    @CsvBindByName
    private String layer;
    @CsvBindByName
    private String exportNameTop;
    @CsvBindByName
    private String effect;
    @CsvBindByName
    private String loopingEffect;
    @CsvBindByName
    private int scale;
    @CsvBindByName
    private int timeMs;
    @CsvBindByName
    private int radius;
    @CsvBindByName
    private int damage;
    @CsvBindByName
    private int customValue;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String bulletExplosionBullet;
    @CsvBindByName
    private int bulletExplosionBulletDistance;
    @CsvBindByName
    private boolean destroysEnvironment;
    @CsvBindByName
    private int pushbackStrength;
    @CsvBindByName
    private int pushbackStrengthSelf;
    @CsvBindByName
    private int freezeStrength;
    @CsvBindByName
    private boolean syncAnimWithServer;
} 