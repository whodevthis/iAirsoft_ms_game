package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.team.InputTeamDto;
import org.project.msgame.application.dtos.team.TeamDetailsDto;
import org.project.msgame.application.dtos.team.TeamDto;
import org.project.msgame.application.ports.in.player.query.GetPlayerByUserIdUseCase;
import org.project.msgame.application.ports.in.team.command.*;
import org.project.msgame.application.ports.in.team.query.GetAllTeamUseCase;
import org.project.msgame.application.ports.in.team.query.GetTeamByIdUseCase;
import org.project.msgame.application.ports.in.team.query.SearchTeamUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private CreateTeamUseCase createTeamUseCase;
    @Mock
    private UpdateTeamUseCase updateTeamUseCase;
    @Mock
    private DeleteTeamUseCase deleteTeamUseCase;
    @Mock
    private AddTeamRoleUseCase addTeamRoleUseCase;
    @Mock
    private RemoveTeamRoleUseCase removeTeamRoleUseCase;
    @Mock
    private AddPlayerOnTeamUseCase addPlayerOnTeamUseCase;
    @Mock
    private RemovePlayerOnTeamUseCase removePlayerOnTeamUseCase;
    @Mock
    private AddObjectiveUseCase addObjectiveUseCase;
    @Mock
    private RemoveObjectiveUseCase removeObjectiveUseCase;
    @Mock
    private AddPlayerObjectiveUseCase addPlayerObjectiveUseCase;
    @Mock
    private RemovePlayerObjectiveUseCase removePlayerObjectiveUseCase;
    @Mock
    private GetAllTeamUseCase getAllTeamUseCase;
    @Mock
    private GetTeamByIdUseCase getTeamByIdUseCase;
    @Mock
    private SearchTeamUseCase searchTeamUseCase;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GetPlayerByUserIdUseCase getPlayerByUserIdUseCase;

    @InjectMocks
    private TeamController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputTeamDto input = mock(InputTeamDto.class);
        UUID id = UUID.randomUUID();
        when(createTeamUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createTeamUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        TeamDetailsDto details = mock(TeamDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateTeamUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateTeamUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteTeamUseCase).delete(id);
    }

    @Test
    void addRole_shouldReturnOk() {
        UUID teamId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        int quantity = 5;

        ResponseEntity<Void> response = controller.addRole(teamId, roleId, quantity);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(addTeamRoleUseCase).addTeamRole(teamId, roleId, quantity);
    }

    @Test
    void removeRole_shouldReturnNoContent() {
        UUID teamId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.removeRole(teamId, roleId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(removeTeamRoleUseCase).removeTeamRole(teamId, roleId);
    }

    @Test
    void addPlayer_shouldResolvePlayerIdAndReturnOk() {
        UUID teamId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        when(getPlayerByUserIdUseCase.getByUserId(userId).id()).thenReturn(playerId);

        ResponseEntity<Void> response = controller.addPlayer(teamId, roleId, userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(addPlayerOnTeamUseCase).addPlayerOnTeam(teamId, playerId, roleId);
    }

    @Test
    void removePlayer_shouldReturnNoContent() {
        UUID teamId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.removePlayer(teamId, playerId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(removePlayerOnTeamUseCase).removePlayerOnTeam(teamId, playerId);
    }

    @Test
    void addObjective_shouldReturnOk() {
        UUID teamId = UUID.randomUUID();
        UUID objectiveId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.addObjective(teamId, objectiveId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(addObjectiveUseCase).addObjective(teamId, objectiveId);
    }

    @Test
    void removeObjective_shouldReturnNoContent() {
        UUID teamId = UUID.randomUUID();
        UUID objectiveId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.removeObjective(teamId, objectiveId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(removeObjectiveUseCase).removeObjective(teamId, objectiveId);
    }

    @Test
    void addPlayerObjective_shouldReturnOk() {
        UUID teamId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID objectiveId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.addPlayerObjective(teamId, roleId, objectiveId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(addPlayerObjectiveUseCase).addPlayerObjective(teamId, roleId, objectiveId);
    }

    @Test
    void removePlayerObjective_shouldReturnNoContent() {
        UUID teamId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID objectiveId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.removePlayerObjective(teamId, roleId, objectiveId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(removePlayerObjectiveUseCase).removePlayerObjective(teamId, roleId, objectiveId);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<TeamDto> list = List.of(mock(TeamDto.class));
        when(getAllTeamUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<TeamDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllTeamUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        TeamDetailsDto details = mock(TeamDetailsDto.class);
        when(getTeamByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<TeamDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getTeamByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "team";
        List<TeamDto> list = List.of(mock(TeamDto.class));
        when(searchTeamUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<TeamDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchTeamUseCase).search(data);
    }
}
