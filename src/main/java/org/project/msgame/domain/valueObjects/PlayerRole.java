package org.project.msgame.domain.valueObjects;

import java.util.UUID;

public record PlayerRole(UUID playerId, UUID roleId) {
    public PlayerRole {
        if (playerId == null) {
            throw new IllegalArgumentException("playerId is null");
        }

        if (roleId == null) {
            throw new IllegalArgumentException("roleId is null");
        }

    }
}
