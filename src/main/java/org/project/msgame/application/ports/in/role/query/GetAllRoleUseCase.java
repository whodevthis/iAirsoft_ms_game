package org.project.msgame.application.ports.in.role.query;

import org.project.msgame.application.dtos.role.RoleDetailsDto;

import java.util.List;

public interface GetAllRoleUseCase {
    List<RoleDetailsDto> getAll();
}
