package com.root7325.javabs.laser.protocol.packets.server;

import com.root7325.javabs.entity.player.Player;
import com.root7325.javabs.laser.protocol.packets.MessageType;
import com.root7325.javabs.laser.protocol.packets.PiranhaMessage;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 26.06.2025
 */
@AllArgsConstructor
public class PlayerProfileMessage extends PiranhaMessage {
    private final Player player;

    @Override
    public void decode(LaserByteBuf in) {

    }

    @Override
    public void encode(LaserByteBuf out) {
        out.writeVLong(player.getId());
        out.writeString(player.getSettings().getUsername());
        out.writeVInt(0);

        out.writeVInt(player.getHeroes().size());
        player.getHeroes().forEach(hero -> {
            out.writeDataReference(hero.getChar());
            out.writeVInt(0);
            out.writeVInt(hero.getScore());
            out.writeVInt(hero.getHighestScore());
            out.writeVInt(hero.getPowerLevel() + 1);
        });

        out.writeVInt(4);
        {
            out.writeDataReference(3, player.getStats().getScore());
            out.writeDataReference(4, player.getStats().getHighestScore());
            out.writeDataReference(5, player.getHeroes().size());
            out.writeDataReference(7, player.getSettings().getThumbnail().createGlobalId());
        }
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.PlayerProfile;
    }
}
