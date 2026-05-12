package application.ports.in.role.command;

import application.dtos.role.InputRoleDto;

import java.util.UUID;

public interface CreateRoleUseCase {
    UUID create(InputRoleDto input);

}
