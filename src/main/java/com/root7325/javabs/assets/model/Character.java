package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Character implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private boolean lockedForChronos;
    @CsvBindByName
    private boolean disabled;
    @CsvBindByName
    private String itemName;
    @CsvBindByName
    private String weaponSkill;
    @CsvBindByName
    private String ultimateSkill;
    @CsvBindByName
    private String pet;
    @CsvBindByName
    private int speed;
    @CsvBindByName
    private int hitpoints;
    @CsvBindByName
    private boolean meleeAutoAttackSplashDamage;
    @CsvBindByName
    private int autoAttackSpeedMs;
    @CsvBindByName
    private int autoAttackDamage;
    @CsvBindByName
    private int autoAttackBulletsPerShot;
    @CsvBindByName
    private String autoAttackMode;
    @CsvBindByName
    private int autoAttackProjectileSpread;
    @CsvBindByName
    private String autoAttackProjectile;
    @CsvBindByName
    private int autoAttackRange;
    @CsvBindByName
    private int regeneratePerSecond;
    @CsvBindByName
    private int ultiChargeMul;
    @CsvBindByName
    private int ultiChargeUltiMul;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private int damagerPercentFromAliens;
    @CsvBindByName
    private String defaultSkin;
    @CsvBindByName
    private String fileName;
    @CsvBindByName
    private String blueExportName;
    @CsvBindByName
    private String redExportName;
    @CsvBindByName
    private String shadowExportName;
    @CsvBindByName
    private String areaEffect;
    @CsvBindByName
    private String deathAreaEffect;
    @CsvBindByName
    private String takeDamageEffect;
    @CsvBindByName
    private String deathEffect;
    @CsvBindByName
    private String moveEffect;
    @CsvBindByName
    private String reloadEffect;
    @CsvBindByName
    private String outOfAmmoEffect;
    @CsvBindByName
    private String dryFireEffect;
    // ... остальные поля ...
} 