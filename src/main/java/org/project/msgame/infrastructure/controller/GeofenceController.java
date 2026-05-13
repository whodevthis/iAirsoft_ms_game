package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.geofence.GeofenceDetailsDto;
import org.project.msgame.application.dtos.geofence.InputGeofenceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.geofence.command.CreateGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.DeleteGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.command.UpdateGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.query.GetAllGeofenceUseCase;
import org.project.msgame.application.ports.in.geofence.query.GetGeofenceByIdUseCase;
import org.project.msgame.application.ports.in.geofence.query.SearchGeofenceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/geofences")
@RequiredArgsConstructor
@Tag(name = "Geofence", description = "Geofence management")
public class GeofenceController {

    private final CreateGeofenceUseCase createGeofenceUseCase;
    private final UpdateGeofenceUseCase updateGeofenceUseCase;
    private final DeleteGeofenceUseCase deleteGeofenceUseCase;
    private final GetAllGeofenceUseCase getAllGeofenceUseCase;
    private final GetGeofenceByIdUseCase getGeofenceByIdUseCase;
    private final SearchGeofenceUseCase searchGeofenceUseCase;

    @PostMapping
    @Operation(summary = "Create a new geofence")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputGeofenceDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createGeofenceUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a geofence")
    public ResponseEntity<UUID> update(@Valid @RequestBody GeofenceDetailsDto dto) {
        return ResponseEntity.ok(updateGeofenceUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a geofence")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteGeofenceUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all geofences")
    public ResponseEntity<List<GeofenceDetailsDto>> getAll() {
        return ResponseEntity.ok(getAllGeofenceUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get geofence by id")
    public ResponseEntity<GeofenceDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getGeofenceByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search geofences")
    public ResponseEntity<List<GeofenceDetailsDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchGeofenceUseCase.search(data));
    }
}
