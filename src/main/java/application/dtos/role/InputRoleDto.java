package application.dtos.role;

import domain.states.RoleType;

public record InputRoleDto(RoleType roleType, String description) {
}
