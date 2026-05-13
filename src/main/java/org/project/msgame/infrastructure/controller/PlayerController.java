package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.player.InputPlayerDto;
import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.player.command.CreatePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.DeletePlayerUseCase;
import org.project.msgame.application.ports.in.player.command.UpdatePlayerUseCase;
import org.project.msgame.application.ports.in.player.query.GetAllPlayerUseCase;
import org.project.msgame.application.ports.in.player.query.GetPlayerByIdUseCase;
import org.project.msgame.application.ports.in.player.query.SearchPlayerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
@Tag(name = "Player", description = "Player management")
public class PlayerController {

    private final CreatePlayerUseCase createPlayerUseCase;
    private final UpdatePlayerUseCase updatePlayerUseCase;
    private final DeletePlayerUseCase deletePlayerUseCase;
    private final GetAllPlayerUseCase getAllPlayerUseCase;
    private final GetPlayerByIdUseCase getPlayerByIdUseCase;
    private final SearchPlayerUseCase searchPlayerUseCase;

    @GetMapping
    @Operation(summary = "Get all players")
    public ResponseEntity<List<PlayerDetailsDto>> getAll() {
        return ResponseEntity.ok(getAllPlayerUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get player by id")
    public ResponseEntity<PlayerDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getPlayerByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search players")
    public ResponseEntity<List<PlayerDetailsDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchPlayerUseCase.search(data));
    }

    @PostMapping
    @Operation(summary = "Create a new player")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputPlayerDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlayerUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a player")
    public ResponseEntity<UUID> update(@Valid @RequestBody PlayerDetailsDto dto) {
        return ResponseEntity.ok(updatePlayerUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deletePlayerUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }


}