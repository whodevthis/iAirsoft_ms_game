package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.cammo.CammoDetailsDto;
import org.project.msgame.application.dtos.cammo.InputCammoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.cammo.command.CreateCammoUseCase;
import org.project.msgame.application.ports.in.cammo.command.DeleteCammoUseCase;
import org.project.msgame.application.ports.in.cammo.command.UpdateCammoUseCase;
import org.project.msgame.application.ports.in.cammo.query.GetAllCammoUseCase;
import org.project.msgame.application.ports.in.cammo.query.GetCammoByIdUseCase;
import org.project.msgame.application.ports.in.cammo.query.SearchCammoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cammos")
@RequiredArgsConstructor
@Tag(name = "Cammo", description = "Camouflage management")
public class CammoController {

    private final CreateCammoUseCase createCammoUseCase;
    private final UpdateCammoUseCase updateCammoUseCase;
    private final DeleteCammoUseCase deleteCammoUseCase;
    private final GetAllCammoUseCase getAllCammoUseCase;
    private final GetCammoByIdUseCase getCammoByIdUseCase;
    private final SearchCammoUseCase searchCammoUseCase;

    @PostMapping
    @Operation(summary = "Create a new cammo")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputCammoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createCammoUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a cammo")
    public ResponseEntity<UUID> update(@Valid @RequestBody CammoDetailsDto dto) {
        return ResponseEntity.ok(updateCammoUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cammo")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCammoUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all cammos")
    public ResponseEntity<List<CammoDetailsDto>> getAll() {
        return ResponseEntity.ok(getAllCammoUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cammo by id")
    public ResponseEntity<CammoDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getCammoByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search cammos")
    public ResponseEntity<List<CammoDetailsDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchCammoUseCase.search(data));
    }
}