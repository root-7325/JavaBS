package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Tile implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String tileCode;
    @CsvBindByName
    private boolean blocksMovement;
    @CsvBindByName
    private boolean blocksProjectiles;
    @CsvBindByName
    private boolean isDestructible;
    @CsvBindByName
    private boolean isDestructibleNormalWeapon;
    @CsvBindByName
    private boolean hidesHero;
    @CsvBindByName
    private int respawnSeconds;
    @CsvBindByName
    private int collisionMargin;
    @CsvBindByName
    private String baseExportName;
    @CsvBindByName
    private String baseExplosionEffect;
    @CsvBindByName
    private String baseHitEffect;
    @CsvBindByName
    private String baseWindEffect;
    @CsvBindByName
    private String baseBulletHole1;
    @CsvBindByName
    private String baseBulletHole2;
    @CsvBindByName
    private String baseCrack1;
    @CsvBindByName
    private String baseCrack2;
    @CsvBindByName
    private int sortOffset;
    @CsvBindByName
    private boolean hasHitAnim;
    @CsvBindByName
    private boolean hasWindAnim;
    @CsvBindByName
    private int shadowScaleX;
    @CsvBindByName
    private int shadowScaleY;
    @CsvBindByName
    private int shadowX;
    @CsvBindByName
    private int shadowY;
    @CsvBindByName
    private int shadowSkew;
}