package domain.valueObjects;

import org.springframework.util.Assert;
import java.util.UUID;

public record PlayerRole(UUID playerId, UUID roleId) {
    public PlayerRole {
        Assert.notNull(playerId, "playerId is null");
        Assert.notNull(roleId, "roleId is null");
    }
}