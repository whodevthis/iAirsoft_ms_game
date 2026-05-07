package application.dtos.role;

import domain.states.RoleType;

import java.util.UUID;

public record RoleDetailsDto(UUID id, RoleType roleType, String description) {
}
