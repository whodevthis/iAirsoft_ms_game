package org.project.msgame.application.mappers;

import org.project.msgame.application.dtos.role.RoleDetailsDto;
import org.project.msgame.domain.aggregates.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleDetailsDto toDetailsDTO(Role role);
}