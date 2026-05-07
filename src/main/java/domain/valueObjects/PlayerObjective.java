package domain.valueObjects;

import org.springframework.util.Assert;
import java.util.UUID;

public record PlayerObjective(PlayerRole playerRole, UUID objectiveId) {
    public PlayerObjective {
        Assert.notNull(playerRole, "playerRole is null");
        Assert.notNull(objectiveId, "objectiveId is null");
    }
}