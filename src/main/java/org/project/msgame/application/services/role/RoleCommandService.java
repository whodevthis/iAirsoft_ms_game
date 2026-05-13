package org.project.msgame.application.services.role;

import org.project.msgame.application.dtos.role.InputRoleDto;
import org.project.msgame.application.dtos.role.RoleDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.ports.in.role.command.CreateRoleUseCase;
import org.project.msgame.application.ports.in.role.command.DeleteRoleUseCase;
import org.project.msgame.application.ports.in.role.command.UpdateRoleUseCase;
import org.project.msgame.application.ports.out.RoleRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleCommandService implements CreateRoleUseCase, DeleteRoleUseCase, UpdateRoleUseCase {

    private final GenericUtils genericUtils;
    private final RoleRepositoryPort roleRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputRoleDto inputRoleDto) {
        Role role = new Role(null, inputRoleDto.roleType(), inputRoleDto.description());
        return roleRepositoryPort.save(role).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        roleRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Role not found: " + id));
        roleRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(RoleDetailsDto roleDetailsDto) {
        Role oldRole = roleRepositoryPort.findById(roleDetailsDto.id()).orElseThrow(() -> new EntityNotFoundException("Role not found"));

        Role updatedRole = new Role(oldRole.getId(),
                genericUtils.applyIfChanged(oldRole.getRoleType(),roleDetailsDto.roleType()),
                genericUtils.applyIfChanged(oldRole.getDescription(),roleDetailsDto.description())
        );

        return roleRepositoryPort.save(updatedRole).getId();
    }


}
