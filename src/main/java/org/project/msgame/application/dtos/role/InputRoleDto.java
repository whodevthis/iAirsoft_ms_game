package org.project.msgame.application.dtos.role;

import org.project.msgame.domain.states.RoleType;

public record InputRoleDto(RoleType roleType, String description) {
}
