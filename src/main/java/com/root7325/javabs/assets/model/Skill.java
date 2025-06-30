package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Skill implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String behaviorType;
    @CsvBindByName
    private boolean canMoveAtSameTime;
    @CsvBindByName
    private boolean targeted;
    @CsvBindByName
    private boolean canAutoShoot;
    @CsvBindByName
    private int cooldown;
    @CsvBindByName
    private int activeTime;
    @CsvBindByName
    private int castingTime;
    @CsvBindByName
    private int castingRange;
    @CsvBindByName
    private int maxCastingRange;
    @CsvBindByName
    private int rechargeTime;
    @CsvBindByName
    private int maxCharge;
    @CsvBindByName
    private int damage;
    @CsvBindByName
    private int msBetweenAttacks;
    @CsvBindByName
    private int spread;
    @CsvBindByName
    private int attackPattern;
    @CsvBindByName
    private int numBulletsInOneAttack;
    @CsvBindByName
    private boolean twoGuns;
    @CsvBindByName
    private boolean executeFirstAttackImmediately;
    @CsvBindByName
    private int chargePushback;
    @CsvBindByName
    private int chargeSpeed;
    @CsvBindByName
    private String chargeType;
    @CsvBindByName
    private int numSpawns;
    @CsvBindByName
    private int maxSpawns;
    @CsvBindByName
    private boolean alwaysCastAtMaxRange;
    @CsvBindByName
    private String projectile;
    @CsvBindByName
    private String summonedCharacter;
    @CsvBindByName
    private String areaEffectObject;
    @CsvBindByName
    private String areaEffectObject2;
    @CsvBindByName
    private String spawnedItem;
    @CsvBindByName
    private String iconSWF;
    @CsvBindByName
    private String iconExportName;
    @CsvBindByName
    private String largeIconSWF;
    @CsvBindByName
    private String largeIconExportName;
    @CsvBindByName
    private String buttonSWF;
    @CsvBindByName
    private String buttonExportName;
    @CsvBindByName
    private String attackEffect;
    @CsvBindByName
    private String useEffect;
    @CsvBindByName
    private String endEffect;
    @CsvBindByName
    private String loopEffect;
    @CsvBindByName
    private String chargeMoveSound;
} 