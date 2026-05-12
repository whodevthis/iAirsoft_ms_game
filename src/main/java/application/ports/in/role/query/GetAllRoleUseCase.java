package application.ports.in.role.query;

import application.dtos.role.RoleDetailsDto;

import java.util.List;

public interface GetAllRoleUseCase {
    List<RoleDetailsDto> getAll();
}
