package org.project.msgame.application.ports.in.role.command;

import org.project.msgame.application.dtos.role.InputRoleDto;

import java.util.UUID;

public interface CreateRoleUseCase {
    UUID create(InputRoleDto input);

}
