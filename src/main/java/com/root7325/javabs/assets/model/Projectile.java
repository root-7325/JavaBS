package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Projectile implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private int speed;
    @CsvBindByName
    private String fileName;
    @CsvBindByName
    private String blueExportName;
    @CsvBindByName
    private String redExportName;
    @CsvBindByName
    private String shadowExportName;
    @CsvBindByName
    private String blueGroundGlowExportName;
    @CsvBindByName
    private String redGroundGlowExportName;
    @CsvBindByName
    private String preExplosionBlueExportName;
    @CsvBindByName
    private String preExplosionRedExportName;
    @CsvBindByName
    private int preExplosionTimeMs;
    @CsvBindByName
    private String hitEffectEnv;
    @CsvBindByName
    private String hitEffectChar;
    @CsvBindByName
    private String maxRangeReachedEffect;
    @CsvBindByName
    private int radius;
    @CsvBindByName
    private boolean indirect;
    @CsvBindByName
    private boolean constantFlyTime;
    @CsvBindByName
    private int triggerWithDelayMs;
    @CsvBindByName
    private int bouncePercent;
    @CsvBindByName
    private int gravity;
    @CsvBindByName
    private int earlyTicks;
    @CsvBindByName
    private int hideTime;
    @CsvBindByName
    private int scale;
    @CsvBindByName
    private int randomStartFrame;
    @CsvBindByName
    private String spawnAreaEffectObject;
    @CsvBindByName
    private String spawnAreaEffectObject2;
    @CsvBindByName
    private String spawnCharacter;
    @CsvBindByName
    private String spawnItem;
    @CsvBindByName
    private String trailEffect;
    @CsvBindByName
    private boolean shotByHero;
    @CsvBindByName
    private boolean isBeam;
    @CsvBindByName
    private boolean isBouncing;
    @CsvBindByName
    private int distanceAddFromBounce;
    @CsvBindByName
    private String rendering;
    @CsvBindByName
    private boolean piercesCharacters;
    @CsvBindByName
    private boolean piercesEnvironment;
    @CsvBindByName
    private boolean piercesEnvironmentLikeButter;
    @CsvBindByName
    private int pushbackStrength;
    @CsvBindByName
    private int chainsToEnemies;
    @CsvBindByName
    private int chainBullets;
    @CsvBindByName
    private int chainSpread;
    @CsvBindByName
    private int chainTravelDistance;
    @CsvBindByName
    private String chainBullet;
    @CsvBindByName
    private int damagePercentStart;
    @CsvBindByName
    private int damagePercentEnd;
    @CsvBindByName
    private boolean damagesConstantly;
    @CsvBindByName
    private int freezeStrength;
    @CsvBindByName
    private int stunLengthMS;
    @CsvBindByName
    private int scaleStart;
    @CsvBindByName
    private int scaleEnd;
    @CsvBindByName
    private boolean attractsPet;
    @CsvBindByName
    private int lifeStealPercent;
    @CsvBindByName
    private boolean passesEnvironment;
    @CsvBindByName
    private int poisonDamagePercent;
    @CsvBindByName
    private int sameProjectileCanNotDamageMS;
    @CsvBindByName
    private int healOwnPercent;
    @CsvBindByName
    private boolean canGrowStronger;
    @CsvBindByName
    private boolean hideFaster;
    @CsvBindByName
    private boolean grapplesEnemy;
} 