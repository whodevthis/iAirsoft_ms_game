package org.project.msgame.application.services.team;

import org.project.msgame.application.dtos.team.TeamDetailsDto;
import org.project.msgame.application.dtos.team.TeamDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.TeamMapper;
import org.project.msgame.application.ports.in.team.query.GetAllTeamUseCase;
import org.project.msgame.application.ports.in.team.query.GetTeamByIdUseCase;
import org.project.msgame.application.ports.in.team.query.SearchTeamUseCase;
import org.project.msgame.application.ports.out.TeamRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamQueryService implements GetAllTeamUseCase, GetTeamByIdUseCase, SearchTeamUseCase {

    private final TeamRepositoryPort teamRepositoryPort;
    private final TeamMapper teamMapper;

    @Override
    public List<TeamDto> getAll(){
        return teamRepositoryPort.findAll().stream().map(teamMapper::toDTO).toList();
    }

    @Override
    public TeamDetailsDto getById(UUID id){
        return teamMapper.toDetailsDTO(teamRepositoryPort.findById(id).orElseThrow(()->new EntityNotFoundException("Team not found")));

    }

    @Override
    public List<TeamDto> search(String data) {
        List<Team> teams = teamRepositoryPort.search(GenericUtils.search(data, Team.class));

        if (teams.isEmpty()) throw new EntityNotFoundException("No teams found for: " + data);

        return teams.stream().map(teamMapper::toDTO).toList();
    }

}
