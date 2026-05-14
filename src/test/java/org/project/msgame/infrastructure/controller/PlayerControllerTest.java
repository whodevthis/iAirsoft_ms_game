package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.player.InputPlayerDto;
import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import org.project.msgame.application.ports.in.player.command.CreatePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.DeletePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.UpdatePlayerUseCase;
import org.project.msgame.application.ports.in.player.query.GetAllPlayerUseCase;
import org.project.msgame.application.ports.in.player.query.GetPlayerByIdUseCase;
import org.project.msgame.application.ports.in.player.query.SearchPlayerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private CreatePlayerUseCase createPlayerUseCase;
    @Mock
    private UpdatePlayerUseCase updatePlayerUseCase;
    @Mock
    private DeletePlayerUseCase deletePlayerUseCase;
    @Mock
    private GetAllPlayerUseCase getAllPlayerUseCase;
    @Mock
    private GetPlayerByIdUseCase getPlayerByIdUseCase;
    @Mock
    private SearchPlayerUseCase searchPlayerUseCase;

    @InjectMocks
    private PlayerController controller;

    @Test
    void getAll_shouldReturnOkWithList() {
        List<PlayerDetailsDto> list = List.of(mock(PlayerDetailsDto.class));
        when(getAllPlayerUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<PlayerDetailsDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllPlayerUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        PlayerDetailsDto details = mock(PlayerDetailsDto.class);
        when(getPlayerByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<PlayerDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getPlayerByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "player";
        List<PlayerDetailsDto> list = List.of(mock(PlayerDetailsDto.class));
        when(searchPlayerUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<PlayerDetailsDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchPlayerUseCase).search(data);
    }

    @Test
    void create_shouldReturnCreatedWithId() {
        InputPlayerDto input = mock(InputPlayerDto.class);
        UUID id = UUID.randomUUID();
        when(createPlayerUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createPlayerUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        PlayerDetailsDto details = mock(PlayerDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updatePlayerUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updatePlayerUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deletePlayerUseCase).delete(id);
    }
}
