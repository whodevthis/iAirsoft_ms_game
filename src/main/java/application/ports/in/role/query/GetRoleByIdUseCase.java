package application.ports.in.role.query;

import application.dtos.role.RoleDetailsDto;

import java.util.UUID;

public interface GetRoleByIdUseCase {
    RoleDetailsDto getById(UUID id);
}
