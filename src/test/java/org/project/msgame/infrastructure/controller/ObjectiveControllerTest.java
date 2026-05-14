package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.objective.InputObjectiveDto;
import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;
import org.project.msgame.application.dtos.objective.ObjectiveDto;
import org.project.msgame.application.ports.in.objective.command.CreateObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.DeleteObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.UpdateObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.query.GetAllObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.query.GetObjectiveByIdUseCase;
import org.project.msgame.application.ports.in.objective.query.SearchObjectiveUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObjectiveControllerTest {

    @Mock
    private CreateObjectiveUseCase createObjectiveUseCase;
    @Mock
    private UpdateObjectiveUseCase updateObjectiveUseCase;
    @Mock
    private DeleteObjectiveUseCase deleteObjectiveUseCase;
    @Mock
    private GetAllObjectiveUseCase getAllObjectiveUseCase;
    @Mock
    private GetObjectiveByIdUseCase getObjectiveByIdUseCase;
    @Mock
    private SearchObjectiveUseCase searchObjectiveUseCase;

    @InjectMocks
    private ObjectiveController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputObjectiveDto input = mock(InputObjectiveDto.class);
        UUID id = UUID.randomUUID();
        when(createObjectiveUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createObjectiveUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        ObjectiveDetailsDto details = mock(ObjectiveDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateObjectiveUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateObjectiveUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteObjectiveUseCase).delete(id);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<ObjectiveDto> list = List.of(mock(ObjectiveDto.class));
        when(getAllObjectiveUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<ObjectiveDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllObjectiveUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        ObjectiveDetailsDto details = mock(ObjectiveDetailsDto.class);
        when(getObjectiveByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<ObjectiveDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getObjectiveByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "objective";
        List<ObjectiveDto> list = List.of(mock(ObjectiveDto.class));
        when(searchObjectiveUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<ObjectiveDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchObjectiveUseCase).search(data);
    }
}
