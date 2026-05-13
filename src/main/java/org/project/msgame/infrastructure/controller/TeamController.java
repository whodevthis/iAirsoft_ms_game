package org.project.msgame.infrastructure.controller;

import org.project.msgame.application.dtos.team.InputTeamDto;
import org.project.msgame.application.dtos.team.TeamDetailsDto;
import org.project.msgame.application.dtos.team.TeamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.msgame.application.ports.in.player.query.GetPlayerByUserIdUseCase;
import org.project.msgame.application.ports.in.team.command.*;
import org.project.msgame.application.ports.in.team.query.GetAllTeamUseCase;
import org.project.msgame.application.ports.in.team.query.GetTeamByIdUseCase;
import org.project.msgame.application.ports.in.team.query.SearchTeamUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@Tag(name = "Team", description = "Team management")
public class TeamController {

    private final CreateTeamUseCase createTeamUseCase;
    private final UpdateTeamUseCase updateTeamUseCase;
    private final DeleteTeamUseCase deleteTeamUseCase;
    private final AddTeamRoleUseCase addTeamRoleUseCase;
    private final RemoveTeamRoleUseCase removeTeamRoleUseCase;
    private final AddPlayerOnTeamUseCase addPlayerOnTeamUseCase;
    private final RemovePlayerOnTeamUseCase removePlayerOnTeamUseCase;
    private final AddObjectiveUseCase addObjectiveUseCase;
    private final RemoveObjectiveUseCase removeObjectiveUseCase;
    private final AddPlayerObjectiveUseCase addPlayerObjectiveUseCase;
    private final RemovePlayerObjectiveUseCase removePlayerObjectiveUseCase;
    private final GetAllTeamUseCase getAllTeamUseCase;
    private final GetTeamByIdUseCase getTeamByIdUseCase;
    private final SearchTeamUseCase searchTeamUseCase;
    private final GetPlayerByUserIdUseCase getPlayerByUserIdUseCase;

    @PostMapping
    @Operation(summary = "Create a new team")
    public ResponseEntity<UUID> create(@Valid @RequestBody InputTeamDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createTeamUseCase.create(dto));
    }

    @PutMapping
    @Operation(summary = "Update a team")
    public ResponseEntity<UUID> update(@Valid @RequestBody TeamDetailsDto dto) {
        return ResponseEntity.ok(updateTeamUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a team")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteTeamUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/roles/{roleId}")
    @Operation(summary = "Add role to team")
    public ResponseEntity<Void> addRole(@PathVariable UUID teamId, @PathVariable UUID roleId,
                                        @RequestParam int quantity) {
        addTeamRoleUseCase.addTeamRole(teamId, roleId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamId}/roles/{roleId}")
    @Operation(summary = "Remove role from team")
    public ResponseEntity<Void> removeRole(@PathVariable UUID teamId, @PathVariable UUID roleId) {
        removeTeamRoleUseCase.removeTeamRole(teamId, roleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/roles/{roleId}/players/{userId}/join")
    @Operation(summary = "Join a team as a player")
    public ResponseEntity<Void> addPlayer(@PathVariable UUID teamId,
                                         @PathVariable UUID roleId,
                                         @PathVariable UUID userId) {
        UUID playerId = getPlayerByUserIdUseCase.getByUserId(userId).id();
        addPlayerOnTeamUseCase.addPlayerOnTeam(teamId, playerId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamId}/players/{playerId}")
    @Operation(summary = "Remove player from team")
    public ResponseEntity<Void> removePlayer(@PathVariable UUID teamId, @PathVariable UUID playerId) {
        removePlayerOnTeamUseCase.removePlayerOnTeam(teamId, playerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/objectives/{objectiveId}")
    @Operation(summary = "Add objective to team")
    public ResponseEntity<Void> addObjective(@PathVariable UUID teamId, @PathVariable UUID objectiveId) {
        addObjectiveUseCase.addObjective(teamId, objectiveId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamId}/objectives/{objectiveId}")
    @Operation(summary = "Remove objective from team")
    public ResponseEntity<Void> removeObjective(@PathVariable UUID teamId, @PathVariable UUID objectiveId) {
        removeObjectiveUseCase.removeObjective(teamId, objectiveId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/roles/{roleId}/objectives/{objectiveId}")
    @Operation(summary = "Add player objective")
    public ResponseEntity<Void> addPlayerObjective(@PathVariable UUID teamId, @PathVariable UUID roleId,
                                                   @PathVariable UUID objectiveId) {
        addPlayerObjectiveUseCase.addPlayerObjective(teamId, roleId, objectiveId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamId}/roles/{roleId}/objectives/{objectiveId}")
    @Operation(summary = "Remove player objective")
    public ResponseEntity<Void> removePlayerObjective(@PathVariable UUID teamId, @PathVariable UUID roleId,
                                                      @PathVariable UUID objectiveId) {
        removePlayerObjectiveUseCase.removePlayerObjective(teamId, roleId, objectiveId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all teams")
    public ResponseEntity<List<TeamDto>> getAll() {
        return ResponseEntity.ok(getAllTeamUseCase.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team by id")
    public ResponseEntity<TeamDetailsDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getTeamByIdUseCase.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search teams")
    public ResponseEntity<List<TeamDto>> search(@RequestParam String data) {
        return ResponseEntity.ok(searchTeamUseCase.search(data));
    }
}