package Application.dtos.role;

import Domain.states.RoleType;

import java.util.UUID;

public record RoleDetailsDto(UUID id, RoleType roleType, String description) {
}
