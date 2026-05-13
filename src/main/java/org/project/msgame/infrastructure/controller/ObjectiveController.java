package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.objective.InputObjectiveDto;
import org.project.msgame.application.dtos.objective.ObjectiveDetailsDto;
import org.project.msgame.application.dtos.objective.ObjectiveDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.objective.command.CreateObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.DeleteObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.command.UpdateObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.query.GetAllObjectiveUseCase;
import org.project.msgame.application.ports.in.objective.query.GetObjectiveByIdUseCase;
import org.project.msgame.application.ports.in.objective.query.SearchObjectiveUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/objectives")
@RequiredArgsConstructor
@Tag(name = "Objective", description = "Objective management")
public class ObjectiveController {

    private final CreateObjectiveUseCase createObjectiveUseCase;
    private final UpdateObjectiveUseCase updateObjectiveUseCase;
    private final DeleteObjectiveUseCase deleteObjectiveUseCase;
    private final GetAllObjectiveUseCase getAllObjectiveUseCase;
    private final GetObjectiveByIdUseCase getObjectiveByIdUseCase;
    private final SearchObjectiveUseCase searchObjectiveUseCase;

    @PostMapping
    @Operation(summary = "Create a new objective")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputObjectiveDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createObjectiveUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update an objective")
    public ResponseEntity<UUID> update(@Valid @RequestBody ObjectiveDetailsDto dto) {
        return ResponseEntity.ok(updateObjectiveUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an objective")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteObjectiveUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all objectives")
    public ResponseEntity<List<ObjectiveDto>> getAll() {
        return ResponseEntity.ok(getAllObjectiveUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get objective by id")
    public ResponseEntity<ObjectiveDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getObjectiveByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search objectives")
    public ResponseEntity<List<ObjectiveDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchObjectiveUseCase.search(data));
    }
}
