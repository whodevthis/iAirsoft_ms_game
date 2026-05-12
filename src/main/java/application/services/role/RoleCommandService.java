package application.services.role;

import application.dtos.role.InputRoleDto;
import application.dtos.role.RoleDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.role.command.CreateRoleUseCase;
import application.ports.in.role.command.DeleteRoleUseCase;
import application.ports.in.role.command.UpdateRoleUseCase;
import application.ports.out.RoleRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Role;
import domain.valueObjects.Marker;
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
