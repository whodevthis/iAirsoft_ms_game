package application.ports.in.game;

import java.util.UUID;

public interface RemoveTeamFromGameUseCase {
    void removeTeam(UUID gameId, UUID teamId);
}