package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;
import org.project.msgame.application.dtos.geofence.InputGeofenceDto;
import org.project.msgame.application.ports.in.geofence.command.CreateGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.DeleteGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.UpdateGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.query.GetAllGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.query.GetGeofenceByIdUseCase;
import org.project.msgame.application.ports.in.geofence.query.SearchGeofenceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeofenceControllerTest {

    @Mock
    private CreateGeofenceUseCase createGeofenceUseCase;
    @Mock
    private UpdateGeofenceUseCase updateGeofenceUseCase;
    @Mock
    private DeleteGeofenceUseCase deleteGeofenceUseCase;
    @Mock
    private GetAllGeofenceUseCase getAllGeofenceUseCase;
    @Mock
    private GetGeofenceByIdUseCase getGeofenceByIdUseCase;
    @Mock
    private SearchGeofenceUseCase searchGeofenceUseCase;

    @InjectMocks
    private GeofenceController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputGeofenceDto input = mock(InputGeofenceDto.class);
        UUID id = UUID.randomUUID();
        when(createGeofenceUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createGeofenceUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        GeofenceDetailsDto details = mock(GeofenceDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateGeofenceUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateGeofenceUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteGeofenceUseCase).delete(id);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<GeofenceDetailsDto> list = List.of(mock(GeofenceDetailsDto.class));
        when(getAllGeofenceUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<GeofenceDetailsDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllGeofenceUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        GeofenceDetailsDto details = mock(GeofenceDetailsDto.class);
        when(getGeofenceByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<GeofenceDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getGeofenceByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "geofence";
        List<GeofenceDetailsDto> list = List.of(mock(GeofenceDetailsDto.class));
        when(searchGeofenceUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<GeofenceDetailsDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchGeofenceUseCase).search(data);
    }
}
