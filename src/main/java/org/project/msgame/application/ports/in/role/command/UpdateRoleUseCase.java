package org.project.msgame.application.ports.in.role.command;

import org.project.msgame.application.dtos.role.RoleDetailsDto;

import java.util.UUID;

public interface UpdateRoleUseCase {
    UUID update(RoleDetailsDto roleDetailsDto);
}