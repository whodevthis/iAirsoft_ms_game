package org.project.msgame.application.services.role;

import org.project.msgame.application.dtos.role.RoleDetailsDto;
import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.application.mappers.RoleMapper;
import org.project.msgame.application.ports.in.role.query.GetAllRoleUseCase;
import org.project.msgame.application.ports.in.role.query.GetRoleByIdUseCase;
import org.project.msgame.application.ports.in.role.query.SearchRoleUseCase;
import org.project.msgame.application.ports.out.RoleRepositoryPort;
import org.project.msgame.application.utils.GenericUtils;
import org.project.msgame.domain.aggregates.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleQueryService implements GetAllRoleUseCase, GetRoleByIdUseCase, SearchRoleUseCase {

    private final RoleMapper roleMapper;
    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public List<RoleDetailsDto> getAll() {
        return roleRepositoryPort.findAll().stream().map(roleMapper::toDetailsDTO).toList();
    }

    @Override
    public RoleDetailsDto getById(UUID id) {
        return roleMapper.toDetailsDTO(roleRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Role not found")));
    }

    @Override
    public List<RoleDetailsDto> search(String data) {
        List<Role> roles = roleRepositoryPort.search(GenericUtils.search(data, Role.class));

        if (roles.isEmpty()) throw new EntityNotFoundException("No role found for: " + data);

        return roles.stream().map(roleMapper::toDetailsDTO).toList();
    }
}
