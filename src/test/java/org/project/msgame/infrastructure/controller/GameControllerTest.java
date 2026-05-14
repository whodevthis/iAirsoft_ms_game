package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.game.GameDetailsDto;
import org.project.msgame.application.dtos.game.GameDto;
import org.project.msgame.application.dtos.game.InputGameDto;
import org.project.msgame.application.ports.in.game.command.*;
import org.project.msgame.application.ports.in.game.query.GetAllGamesUseCase;
import org.project.msgame.application.ports.in.game.query.GetGameByIdUseCase;
import org.project.msgame.application.ports.in.game.query.SearchGameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private CreateGameUseCase createGameUseCase;
    @Mock
    private UpdateGameUseCase updateGameUseCase;
    @Mock
    private DeleteGameUseCase deleteGameUseCase;
    @Mock
    private AddTeamToGameUseCase addTeamToGameUseCase;
    @Mock
    private RemoveTeamFromGameUseCase removeTeamFromGameUseCase;
    @Mock
    private GetAllGamesUseCase getAllGamesUseCase;
    @Mock
    private GetGameByIdUseCase getGameByIdUseCase;
    @Mock
    private SearchGameUseCase searchGameUseCase;

    @InjectMocks
    private GameController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputGameDto input = mock(InputGameDto.class);
        UUID id = UUID.randomUUID();
        when(createGameUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createGameUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        GameDetailsDto details = mock(GameDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateGameUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateGameUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteGameUseCase).delete(id);
    }

    @Test
    void addTeam_shouldReturnOk() {
        UUID gameId = UUID.randomUUID();
        UUID teamId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.addTeam(gameId, teamId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(addTeamToGameUseCase).addTeam(gameId, teamId);
    }

    @Test
    void removeTeam_shouldReturnNoContent() {
        UUID gameId = UUID.randomUUID();
        UUID teamId = UUID.randomUUID();

        ResponseEntity<Void> response = controller.removeTeam(gameId, teamId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(removeTeamFromGameUseCase).removeTeam(gameId, teamId);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<GameDto> list = List.of(mock(GameDto.class));
        when(getAllGamesUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<GameDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllGamesUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        GameDetailsDto details = mock(GameDetailsDto.class);
        when(getGameByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<GameDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getGameByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "game";
        List<GameDto> list = List.of(mock(GameDto.class));
        when(searchGameUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<GameDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchGameUseCase).search(data);
    }
}
