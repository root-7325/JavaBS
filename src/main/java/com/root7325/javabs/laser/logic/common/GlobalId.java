package com.root7325.javabs.laser.logic.common;

import com.root7325.javabs.assets.model.AssetType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author root7325 on 19.06.2025
 */
@Getter
@AllArgsConstructor
public class GlobalId {
    private final int classId;
    private final int instanceId;

    public GlobalId(AssetType assetType, int instanceId) {
        this.classId = assetType.getId();
        this.instanceId = instanceId;
    }

    public GlobalId(int[] ref) {
        if (ref[0] != 0) {
            this.classId = ref[0];
            this.instanceId = ref[1];
        } else {
            this.classId = 0;
            this.instanceId = 0;
        }
    }

    public int createGlobalId() {
        return classId * 1000000 + instanceId;
    }
}
