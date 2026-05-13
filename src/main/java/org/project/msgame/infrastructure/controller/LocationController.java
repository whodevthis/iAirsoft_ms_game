package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.location.InputLocationDto;
import org.project.msgame.application.dtos.location.LocationDetailsDto;
import org.project.msgame.application.dtos.location.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.location.command.CreateLocationUseCase;
import org.project.msgame.application.ports.in.location.command.DeleteLocationUseCase;
import org.project.msgame.application.ports.in.location.command.UpdateLocationUseCase;
import org.project.msgame.application.ports.in.location.query.GetAllLocationUseCase;
import org.project.msgame.application.ports.in.location.query.GetLocationByIdUseCase;
import org.project.msgame.application.ports.in.location.query.SearchLocationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
@Tag(name = "Location", description = "Location management")
public class LocationController {

    private final CreateLocationUseCase createLocationUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;
    private final DeleteLocationUseCase deleteLocationUseCase;
    private final GetAllLocationUseCase getAllLocationUseCase;
    private final GetLocationByIdUseCase getLocationByIdUseCase;
    private final SearchLocationUseCase searchLocationUseCase;

    @PostMapping
    @Operation(summary = "Create a new location")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputLocationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createLocationUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a location")
    public ResponseEntity<UUID> update(@Valid @RequestBody LocationDetailsDto dto) {
        return ResponseEntity.ok(updateLocationUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a location")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteLocationUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all locations")
    public ResponseEntity<List<LocationDto>> getAll() {
        return ResponseEntity.ok(getAllLocationUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get location by id")
    public ResponseEntity<LocationDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getLocationByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search locations")
    public ResponseEntity<List<LocationDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchLocationUseCase.search(data));
    }
}
