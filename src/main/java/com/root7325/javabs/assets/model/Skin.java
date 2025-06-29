package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class Skin implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private boolean lockedForChronos;
    @CsvBindByName
    private String character;
    @CsvBindByName
    private String petSkin;
    @CsvBindByName
    private int costGems;
    @CsvBindByName
    private int priority;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private String model;
    @CsvBindByName
    private String blueTexture;
    @CsvBindByName
    private String redTexture;
    @CsvBindByName
    private String blueSpecular;
    @CsvBindByName
    private String redSpecular;
    @CsvBindByName
    private String idleAnim;
    @CsvBindByName
    private String walkAnim;
    @CsvBindByName
    private String primarySkillAnim;
    @CsvBindByName
    private String secondarySkillAnim;
    @CsvBindByName
    private String primarySkillRecoilAnim;
    @CsvBindByName
    private String primarySkillRecoilAnim2;
    @CsvBindByName
    private String secondarySkillRecoilAnim;
    @CsvBindByName
    private String secondarySkillRecoilAnim2;
    @CsvBindByName
    private String reloadingAnim;
    @CsvBindByName
    private String pushbackAnim;
    @CsvBindByName
    private String happyAnim;
    @CsvBindByName
    private String happyLoopAnim;
    @CsvBindByName
    private String sadAnim;
    @CsvBindByName
    private String sadLoopAnim;
    @CsvBindByName
    private String heroScreenIdleAnim;
    @CsvBindByName
    private String heroScreenAnim;
    @CsvBindByName
    private String heroScreenLoopAnim;
    @CsvBindByName
    private String signatureAnim;
    @CsvBindByName
    private String enterAnim;
    @CsvBindByName
    private String bossAutoAttackAnim;
    @CsvBindByName
    private String bossAutoAttackRecoilAnim;
    @CsvBindByName
    private String bossAutoAttackRecoilAnim2;
    @CsvBindByName
    private String idleFace;
    @CsvBindByName
    private String walkFace;
    @CsvBindByName
    private String happyFace;
    @CsvBindByName
    private String happyLoopFace;
    @CsvBindByName
    private String sadFace;
    @CsvBindByName
    private String sadLoopFace;
    @CsvBindByName
    private String heroScreenIdleFace;
    @CsvBindByName
    private String heroScreenFace;
    @CsvBindByName
    private String heroScreenLoopFace;
    @CsvBindByName
    private String signatureFace;
    @CsvBindByName
    private int headRotationIngame;
    @CsvBindByName
    private int hatScaleIngame;
    @CsvBindByName
    private boolean petInSameSprite;
    @CsvBindByName
    private String mainAttackProjectile;
    @CsvBindByName
    private String ultiProjectile;
    @CsvBindByName
    private String mainAttackEffect;
    @CsvBindByName
    private String ultiAttackEffect;
} 