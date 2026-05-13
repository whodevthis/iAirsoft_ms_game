package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.game.GameDetailsDto;
import org.project.msgame.application.dtos.game.GameDto;
import org.project.msgame.application.dtos.game.InputGameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.game.command.*;
import org.project.msgame.application.ports.in.game.query.GetAllGamesUseCase;
import org.project.msgame.application.ports.in.game.query.GetGameByIdUseCase;
import org.project.msgame.application.ports.in.game.query.SearchGameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
@Tag(name = "Game", description = "Game management")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;
    private final AddTeamToGameUseCase addTeamToGameUseCase;
    private final RemoveTeamFromGameUseCase removeTeamFromGameUseCase;
    private final GetAllGamesUseCase getAllGamesUseCase;
    private final GetGameByIdUseCase getGameByIdUseCase;
    private final SearchGameUseCase searchGameUseCase;

    @PostMapping
    @Operation(summary = "Create a new game")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputGameDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createGameUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a game")
    public ResponseEntity<UUID> update(@Valid @RequestBody GameDetailsDto dto) {
        return ResponseEntity.ok(updateGameUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a game")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteGameUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/teams/{teamId}")
    @Operation(summary = "Add team to game")
    public ResponseEntity<Void> addTeam(@PathVariable UUID gameId, @PathVariable UUID teamId) {
        addTeamToGameUseCase.addTeam(gameId, teamId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gameId}/teams/{teamId}")
    @Operation(summary = "Remove team from game")
    public ResponseEntity<Void> removeTeam(@PathVariable UUID gameId, @PathVariable UUID teamId) {
        removeTeamFromGameUseCase.removeTeam(gameId, teamId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all games")
    public ResponseEntity<List<GameDto>> getAll() {
        return ResponseEntity.ok(getAllGamesUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get game by id")
    public ResponseEntity<GameDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getGameByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search games")
    public ResponseEntity<List<GameDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchGameUseCase.search(data));
    }
}