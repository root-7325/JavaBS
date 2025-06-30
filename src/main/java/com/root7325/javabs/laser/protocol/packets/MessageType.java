package com.root7325.javabs.laser.protocol.packets;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author root7325 on 17.06.2025
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    ClientHello(10100),
    Login(10101),
    ClientCapabilities(10107),
    KeepAlive(10108),
    AnalyticEvent(10110),
    ChangeAvatarName(10212),
    EndClientTurn(14102),
    MatchmakeRequest(14103),
    CancelMatchmaking(14106),
    GoHomeFromOfflinePractise(14109),
    GetPlayerProfile(14113),
    TeamCreate(14350),

    ServerHello(20100),
    LoginFailed(20103),
    LoginOk(20104),
    KeepAliveServer(20108),
    MatchMakingCancelled(20406),
    OwnHomeData(24101),
    MatchmakeFailed(24108),
    AvailableServerCommand(24111),
    PlayerProfile(24113),
    TeamError(24129);

    private final int i;

    public static MessageType from(int type) {
        for (MessageType messageType : values()) {
            if (messageType.i == type) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("No MessageType with type " + type);
    }
}
