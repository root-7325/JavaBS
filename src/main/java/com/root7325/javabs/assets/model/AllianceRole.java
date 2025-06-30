package com.root7325.javabs.assets.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author root7325 on 28.06.2025
 */
@Data
public class AllianceRole implements Asset {
    private int id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private int level;
    @CsvBindByName
    private String tid;
    @CsvBindByName
    private boolean canInvite;
    @CsvBindByName
    private boolean canSendMail;
    @CsvBindByName
    private boolean canChangeAllianceSettings;
    @CsvBindByName
    private boolean canAcceptJoinRequest;
    @CsvBindByName
    private boolean canKick;
    @CsvBindByName
    private boolean canBePromotedToLeader;
    @CsvBindByName
    private int promoteSkill;
} 