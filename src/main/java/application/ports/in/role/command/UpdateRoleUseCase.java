package application.ports.in.role.command;

import application.dtos.role.RoleDetailsDto;

import java.util.UUID;

public interface UpdateRoleUseCase {
    UUID update(RoleDetailsDto roleDetailsDto);
}