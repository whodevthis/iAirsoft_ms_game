package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.role.InputRoleDto;
import org.project.msgame.application.dtos.role.RoleDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.role.command.CreateRoleUseCase;
import org.project.msgame.application.ports.in.role.command.DeleteRoleUseCase;
import org.project.msgame.application.ports.in.role.command.UpdateRoleUseCase;
import org.project.msgame.application.ports.in.role.query.GetAllRoleUseCase;
import org.project.msgame.application.ports.in.role.query.GetRoleByIdUseCase;
import org.project.msgame.application.ports.in.role.query.SearchRoleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Role", description = "Role management")
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final GetAllRoleUseCase getAllRoleUseCase;
    private final GetRoleByIdUseCase getRoleByIdUseCase;
    private final SearchRoleUseCase searchRoleUseCase;

    @PostMapping
    @Operation(summary = "Create a new role")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputRoleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createRoleUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a role")
    public ResponseEntity<UUID> update(@Valid @RequestBody RoleDetailsDto dto) {
        return ResponseEntity.ok(updateRoleUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a role")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteRoleUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all roles")
    public ResponseEntity<List<RoleDetailsDto>> getAll() {
        return ResponseEntity.ok(getAllRoleUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by id")
    public ResponseEntity<RoleDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getRoleByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search roles")
    public ResponseEntity<List<RoleDetailsDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchRoleUseCase.search(data));
    }
}