package application.services.team;

import application.dtos.team.InputTeamDto;
import application.dtos.team.TeamDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.team.command.*;
import application.ports.out.TeamRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Team;
import domain.service.TeamDomainService;
import domain.valueObjects.Marker;
import domain.valueObjects.Respawn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamCommandService implements CreateTeamUseCase , UpdateTeamUseCase, DeleteTeamUseCase, AddTeamRoleUseCase,
        RemoveTeamRoleUseCase ,AddPlayerOnTeamUseCase, RemovePlayerOnTeamUseCase, AddObjectiveUseCase, RemoveObjectiveUseCase,
        AddPlayerObjectiveUseCase,RemovePlayerObjectiveUseCase{
    private final GenericUtils genericUtils;

    private final TeamDomainService teamDomainService;
    private final TeamRepositoryPort teamRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputTeamDto input) {
        Marker marker = new Marker(input.lonRespawn(), input.latRespawn());
        Respawn respawn = new Respawn(marker);
        Team team = new Team(null, input.name(), input.description(), input.imagePath(), input.cammoId(), respawn);
        return teamRepositoryPort.save(team).getId();
    }

    @Transactional
    @Override
    public UUID update(TeamDetailsDto teamDetailsDto) {
        Team oldTeam = teamRepositoryPort.findById(teamDetailsDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        Team updatedTeam = new Team(
                genericUtils.applyIfChanged(oldTeam.getCammoId(), teamDetailsDto.cammoId()),
                genericUtils.applyIfChanged(oldTeam.getDescription(), teamDetailsDto.description()),
                oldTeam.getId(),
                genericUtils.applyIfChanged(oldTeam.getImagePath(), teamDetailsDto.imagePath()),
                genericUtils.applyIfChanged(oldTeam.getName(), teamDetailsDto.name()),
                oldTeam.getObjectives(),
                oldTeam.getPlayerObjectives(),
                oldTeam.getPlayers(),
                genericUtils.applyIfChanged(oldTeam.getRespawn(), teamDetailsDto.respawn()),
                oldTeam.getRoles()
        );

        return teamRepositoryPort.save(updatedTeam).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        teamRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Team not found: " + id));
        teamRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public void addTeamRole(UUID teamId, UUID roleId, int quantity) {
        Team team = getEntity(teamId);
        teamDomainService.addTeamRole(team, roleId, quantity);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void removeTeamRole(UUID teamId, UUID roleId){
        Team team = getEntity(teamId);
        teamDomainService.removeTeamRole(team,roleId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void addPlayerOnTeam(UUID teamId, UUID playerId, UUID roleId){
        Team team = getEntity(teamId);
        teamDomainService.addPlayerOnTeam(team,  playerId,  roleId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void removePlayerOnTeam(UUID teamId, UUID playerId){
        Team team = getEntity(teamId);
        teamDomainService.removePlayerOnTeam(team,  playerId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void addObjective(UUID teamId ,UUID objectiveId){
        Team team = getEntity(teamId);
        teamDomainService.addObjective(team,  objectiveId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void removeObjective(UUID teamId, UUID objectiveId){
        Team team = getEntity(teamId);
        teamDomainService.removeObjective(team,  objectiveId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void addPlayerObjective(UUID teamId, UUID roleId, UUID objectiveId){
        Team team = getEntity(teamId);
        teamDomainService.addPlayerObjective(team,   roleId,  objectiveId);
        teamRepositoryPort.save(team);
    }

    @Transactional
    @Override
    public void removePlayerObjective(UUID teamId, UUID roleId, UUID objectiveId){
        Team team = getEntity(teamId);
        teamDomainService.removePlayerObjective(team,   roleId,  objectiveId);
        teamRepositoryPort.save(team);

    }

    private Team getEntity (UUID teamId){
        return teamRepositoryPort.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team not found: " + teamId));
    }
}
