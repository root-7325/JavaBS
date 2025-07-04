package com.root7325.javabs.entity.player;

import com.root7325.javabs.laser.logic.common.GlobalId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * @author root7325 on 22.06.2025
 */
@Slf4j
@Data
@Embeddable
public class PlayerSettings {
    @Column(length = 32)
    private String username = "Brawler";

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private boolean registered = false;

    @Column(name = "thumbnail_id")
    private int thumbnailId;

    @Transient
    private GlobalId thumbnail;

    @Column(name = "supported_content_creator", length = 32)
    private String supportedContentCreator = "";

    @Transient
    private int tutorialStep = 2;

    public PlayerSettings() {
        this.setThumbnail(new GlobalId(28, 0));
    }

    protected void syncFields() {
        setThumbnailId(thumbnail.getInstanceId());
    }

    @PostLoad
    void postLoad() {
        setThumbnail(new GlobalId(28, thumbnailId));
    }
}
