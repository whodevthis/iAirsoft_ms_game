package application.services.team;

import application.dtos.team.TeamDetailsDto;
import application.dtos.team.TeamDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.TeamMapper;
import application.ports.in.team.query.GetAllTeamUseCase;
import application.ports.in.team.query.GetTeamByIdUseCase;
import application.ports.in.team.query.SearchTeamUseCase;
import application.ports.out.TeamRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Team;
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
    public List<TeamDto> searchTeam(String data) {
        List<Team> teams = teamRepositoryPort.search(GenericUtils.search(data, Team.class));

        if (teams.isEmpty()) throw new EntityNotFoundException("No teams found for: " + data);

        return teams.stream().map(teamMapper::toDTO).toList();
    }

}
