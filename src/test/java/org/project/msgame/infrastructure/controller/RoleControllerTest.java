package org.project.msgame.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.msgame.application.dtos.role.InputRoleDto;
import org.project.msgame.application.dtos.role.RoleDetailsDto;
import org.project.msgame.application.ports.in.role.command.CreateRoleUseCase;
import org.project.msgame.application.ports.in.role.command.DeleteRoleUseCase;
import org.project.msgame.application.ports.in.role.command.UpdateRoleUseCase;
import org.project.msgame.application.ports.in.role.query.GetAllRoleUseCase;
import org.project.msgame.application.ports.in.role.query.GetRoleByIdUseCase;
import org.project.msgame.application.ports.in.role.query.SearchRoleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    private CreateRoleUseCase createRoleUseCase;
    @Mock
    private UpdateRoleUseCase updateRoleUseCase;
    @Mock
    private DeleteRoleUseCase deleteRoleUseCase;
    @Mock
    private GetAllRoleUseCase getAllRoleUseCase;
    @Mock
    private GetRoleByIdUseCase getRoleByIdUseCase;
    @Mock
    private SearchRoleUseCase searchRoleUseCase;

    @InjectMocks
    private RoleController controller;

    @Test
    void create_shouldReturnCreatedWithId() {
        InputRoleDto input = mock(InputRoleDto.class);
        UUID id = UUID.randomUUID();
        when(createRoleUseCase.create(input)).thenReturn(id);

        ResponseEntity<UUID> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(id);
        verify(createRoleUseCase).create(input);
    }

    @Test
    void update_shouldReturnOkWithId() {
        RoleDetailsDto details = mock(RoleDetailsDto.class);
        UUID id = UUID.randomUUID();
        when(updateRoleUseCase.update(details)).thenReturn(id);

        ResponseEntity<UUID> response = controller.update(details);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(id);
        verify(updateRoleUseCase).update(details);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(deleteRoleUseCase).delete(id);
    }

    @Test
    void getAll_shouldReturnOkWithList() {
        List<RoleDetailsDto> list = List.of(mock(RoleDetailsDto.class));
        when(getAllRoleUseCase.getAll()).thenReturn(list);

        ResponseEntity<List<RoleDetailsDto>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(getAllRoleUseCase).getAll();
    }

    @Test
    void getById_shouldReturnOkWithDto() {
        UUID id = UUID.randomUUID();
        RoleDetailsDto details = mock(RoleDetailsDto.class);
        when(getRoleByIdUseCase.getById(id)).thenReturn(details);

        ResponseEntity<RoleDetailsDto> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(details);
        verify(getRoleByIdUseCase).getById(id);
    }

    @Test
    void search_shouldReturnOkWithList() {
        String data = "role";
        List<RoleDetailsDto> list = List.of(mock(RoleDetailsDto.class));
        when(searchRoleUseCase.search(data)).thenReturn(list);

        ResponseEntity<List<RoleDetailsDto>> response = controller.search(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(searchRoleUseCase).search(data);
    }
}
