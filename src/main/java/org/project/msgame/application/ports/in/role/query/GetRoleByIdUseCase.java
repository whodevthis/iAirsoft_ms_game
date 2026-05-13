package org.project.msgame.application.ports.in.role.query;

import org.project.msgame.application.dtos.role.RoleDetailsDto;

import java.util.UUID;

public interface GetRoleByIdUseCase {
    RoleDetailsDto getById(UUID id);
}
