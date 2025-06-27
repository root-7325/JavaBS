package com.root7325.javabs.entity.player;

import com.root7325.javabs.entity.Hero;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author root7325 on 17.06.2025
 */
@Data
@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;

    @Embedded
    private PlayerStats stats;

    @Embedded
    private PlayerSettings settings;

    @Embedded
    private PlayerResources resources;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hero> heroes;

    public Player() {
        setToken(UUID.randomUUID().toString());
        setSettings(new PlayerSettings());
        setStats(new PlayerStats());
        setResources(new PlayerResources());

        this.heroes = new ArrayList<>();
        heroes.add(new Hero(this,0, 0));
    }
}
