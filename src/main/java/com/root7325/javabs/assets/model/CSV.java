package com.root7325.javabs.assets.model;

import lombok.Getter;

import java.io.File;

/**
 * @author root7325 on 01.01.2025
 */
@Getter
public enum CSV {
    SkinConfs(-1, "logic", "skin_confs"),
    GameModeVariations(-1, "logic", "game_mode_variations"),
    Milestones(39, "logic", "milestones"),
    Locales(1),
    Globals(2),
    BillingPackages(3),
    Sounds(4),
    Resources(5),
    Projectiles(6),
    Effects(7),
    AllianceBadges(8),
    ClientGlobals(9),
    ParticleEmitters(10),
    HealthBars(11),
    Music(12),
    Credits(13),
    Regions(14),
    Locations(15, "logic", "locations"),
    Characters(16, "logic", "characters"),
    AreaEffects(17),
    Items(18),
    Maps(19),
    Skills(20),
    Campaign(21),
    Bosses(22),
    Cards(23, "logic", "cards"),
    Animations(24),
    AllianceRoles(25),
    Tutorial(26),
    Tiles(27),
    PlayerThumbnails(28, "logic", "player_thumbnails"),
    Skins(29, "logic", "skins"),
    Faces(30),
    Themes(41, "logic", "themes"),
    NameColors(43, "logic", "name_colors");

    private final int id;
    private final String type;
    private final String file;

    CSV(int id, String type, String file) {
        this.id = id;
        this.type = type;
        this.file = file;
    }

    CSV(int id) {
        this.id = id;
        this.type = null;
        this.file = null;
    }

    public String getRelativePath() {
        return String.format("csv_%s%s%s.csv", type, File.separator, file);
    }
}
