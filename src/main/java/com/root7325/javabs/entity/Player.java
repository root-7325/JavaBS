package com.root7325.javabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * @author root7325 on 17.06.2025
 */
@Data
@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    private String username;

    @PrePersist
    private void prePersist() {
        setToken(UUID.randomUUID().toString());
        setUsername("JavaBS");
    }
}
