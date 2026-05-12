package application.ports.in.team.command;

import java.util.UUID;

public interface DeleteTeamUseCase {
    void deleteTeam (UUID id);
}
