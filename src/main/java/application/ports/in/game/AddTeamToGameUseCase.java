package application.ports.in.game;

import java.util.UUID;

public interface AddTeamToGameUseCase {
    void addTeam(UUID gameId, UUID teamId);
}