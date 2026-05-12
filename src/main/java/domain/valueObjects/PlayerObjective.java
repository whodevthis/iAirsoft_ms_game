package domain.valueObjects;

import java.util.UUID;

public record PlayerObjective(PlayerRole playerRole, UUID objectiveId) {
    public PlayerObjective {
        if (playerRole == null) {
            throw new IllegalArgumentException("playerRole is null");
        }
        if (objectiveId == null) {
            throw new IllegalArgumentException("objectiveId is null");
        }
    }
}