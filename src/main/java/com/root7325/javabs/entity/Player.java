package com.root7325.javabs.entity;

import com.root7325.javabs.laser.logic.commons.GlobalId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author root7325 on 17.06.2025
 */
@Data
@Entity
@Slf4j
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    private String username;

    private boolean registered;

    @Column(name = "thumbnail_id")
    private int thumbnailId;

    @Transient
    private GlobalId thumbnail;

    private int score;

    private int highestScore;

    private int boxTokens;
    private int bigBoxTokens;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Hero> heroes;

    public Player() {
        setToken(UUID.randomUUID().toString());
        setUsername("JavaBS");
        setThumbnail(new GlobalId(28, 0));

        this.heroes = new ArrayList<>();
        heroes.add(new Hero(this,0, 0));
    }

    @PostLoad
    void postLoad() {
        setThumbnail(new GlobalId(28, thumbnailId));
    }
}
