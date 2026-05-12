package application.services.team;


import application.dtos.team.InputTeamDto;
import application.dtos.team.TeamDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.TeamMapper;
import application.ports.in.team.CreateTeamUseCase;
import application.ports.out.TeamRepositoryPort;
import domain.aggregates.Role;
import domain.aggregates.Team;
import domain.valueObjects.Marker;
import domain.valueObjects.PlayerRole;
import domain.valueObjects.Respawn;
import domain.valueObjects.TeamRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamCommandService implements CreateTeamUseCase {

    private final TeamMapper teamMapper;
    private final TeamRepositoryPort teamRepositoryPort;


    @Transactional
    @Override
    public UUID createTeam(InputTeamDto input) {
        Marker marker = new Marker(input.lonRespawn(), input.latRespawn());
        Respawn respawn = new Respawn(marker);
        Team team = new Team(
                null,
                input.name(),
                input.description(),
                input.imagePath(),
                input.cammoId(),
                respawn
        );
        return teamRepositoryPort.save(team).getId();
    }





}
