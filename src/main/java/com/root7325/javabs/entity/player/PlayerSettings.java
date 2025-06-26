package com.root7325.javabs.entity.player;

import com.root7325.javabs.laser.logic.commons.GlobalId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * @author root7325 on 22.06.2025
 */
@Data
@Embeddable
public class PlayerSettings {
    @Column(length = 128)
    private String username = "JavaBS";

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

    @PostLoad
    void postLoad() {
        setThumbnail(new GlobalId(28, thumbnailId));
    }
}
