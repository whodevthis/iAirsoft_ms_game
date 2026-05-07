package Application.dtos.role;

import Domain.states.RoleType;

public record InputRoleDto(RoleType roleType, String description) {
}
