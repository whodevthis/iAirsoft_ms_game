package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.cammo.CammoDetailsDto;
import org.project.msgame.application.dtos.cammo.InputCammoDto;
import org.project.msgame.application.ports.in.cammo.command.CreateCammoUseCase;
import org.project.msgame.application.ports.in.cammo.command.DeleteCammoUseCase;
import org.project.msgame.application.ports.in.cammo.command.UpdateCammoUseCase;
import org.project.msgame.application.ports.in.cammo.query.GetAllCammoUseCase;
import org.project.msgame.application.ports.in.cammo.query.GetCammoByIdUseCase;
import org.project.msgame.application.ports.in.cammo.query.SearchCammoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CammoControllerTest {

    @Mock
    private CreateCammoUseCase createCammoUseCase;
    @Mock
    private UpdateCammoUseCase updateCammoUseCase;
    @Mock
    private DeleteCammoUseCase deleteCammoUseCase;
    @Mock
    private GetAllCammoUseCase getAllCammoUseCase;
    @Mock
    private GetCammoByIdUseCase getCammoByIdUseCase;
    @Mock
    private SearchCammoUseCase searchCammoUseCase;

    @InjectMocks
    private CammoController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputCammoDto input = mock(InputCammoDto.class);
        UUID id = UUID.randomUUID();
        when(createCammoUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createCammoUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        CammoDetailsDto details = mock(CammoDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateCammoUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateCammoUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteCammoUseCase).delete(id);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<CammoDetailsDto> list = List.of(mock(CammoDetailsDto.class));
        when(getAllCammoUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<CammoDetailsDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllCammoUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        CammoDetailsDto details = mock(CammoDetailsDto.class);
        when(getCammoByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<CammoDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getCammoByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "cammo";
        List<CammoDetailsDto> list = List.of(mock(CammoDetailsDto.class));
        when(searchCammoUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<CammoDetailsDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchCammoUseCase).search(data);
    }
}
