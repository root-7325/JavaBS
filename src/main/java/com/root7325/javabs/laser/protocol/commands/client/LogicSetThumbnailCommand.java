package com.root7325.javabs.laser.protocol.commands.client;

import com.google.inject.Inject;
import com.root7325.javabs.assets.manager.thumbnail.ThumbnailManager;
import com.root7325.javabs.assets.model.AssetType;
import com.root7325.javabs.laser.logic.common.GlobalId;
import com.root7325.javabs.laser.protocol.commands.CommandType;
import com.root7325.javabs.laser.protocol.commands.LogicCommand;
import com.root7325.javabs.utils.LaserByteBuf;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author root7325 on 29.06.2025
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class LogicSetThumbnailCommand extends LogicCommand {
    private final ThumbnailManager thumbnailManager;
    private GlobalId thumbnail;

    @Override
    public void decode(LaserByteBuf in) {
        super.decode(in);
        this.thumbnail = in.readDataReference();
    }


    @Override
    public int execute() {
        if (!isThumbnailValid()) {
            log.warn("Invalid thumbnail! {}", thumbnail);
            return -1;
        }

        player.getSettings().setThumbnail(thumbnail);
        return 0;
    }

    public boolean isThumbnailValid() {
        return thumbnail.getClassId() == AssetType.PlayerThumbnails.getId()
                && thumbnail.getInstanceId() >= 0 && thumbnail.getInstanceId() <= thumbnailManager.getAll().size();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SetPlayerThumbnail;
    }
}
