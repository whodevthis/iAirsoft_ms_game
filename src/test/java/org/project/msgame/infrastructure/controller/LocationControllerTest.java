package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.location.InputLocationDto;
import org.project.msgame.application.dtos.location.LocationDetailsDto;
import org.project.msgame.application.dtos.location.LocationDto;
import org.project.msgame.application.ports.in.location.command.CreateLocationUseCase;
import org.project.msgame.application.ports.in.location.command.DeleteLocationUseCase;
import org.project.msgame.application.ports.in.location.command.UpdateLocationUseCase;
import org.project.msgame.application.ports.in.location.query.GetAllLocationUseCase;
import org.project.msgame.application.ports.in.location.query.GetLocationByIdUseCase;
import org.project.msgame.application.ports.in.location.query.SearchLocationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private CreateLocationUseCase createLocationUseCase;
    @Mock
    private UpdateLocationUseCase updateLocationUseCase;
    @Mock
    private DeleteLocationUseCase deleteLocationUseCase;
    @Mock
    private GetAllLocationUseCase getAllLocationUseCase;
    @Mock
    private GetLocationByIdUseCase getLocationByIdUseCase;
    @Mock
    private SearchLocationUseCase searchLocationUseCase;

    @InjectMocks
    private LocationController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputLocationDto input = mock(InputLocationDto.class);
        UUID id = UUID.randomUUID();
        when(createLocationUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createLocationUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        LocationDetailsDto details = mock(LocationDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateLocationUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateLocationUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteLocationUseCase).delete(id);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<LocationDto> list = List.of(mock(LocationDto.class));
        when(getAllLocationUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<LocationDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllLocationUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        LocationDetailsDto details = mock(LocationDetailsDto.class);
        when(getLocationByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<LocationDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getLocationByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "location";
        List<LocationDto> list = List.of(mock(LocationDto.class));
        when(searchLocationUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<LocationDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchLocationUseCase).search(data);
    }
}
