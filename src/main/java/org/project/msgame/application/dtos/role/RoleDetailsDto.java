package org.project.msgame.application.dtos.role;

import org.project.msgame.domain.states.RoleType;

import java.util.UUID;

public record RoleDetailsDto(UUID id, RoleType roleType, String description) {
}
