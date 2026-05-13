package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Role;
import org.project.msgame.infrastructure.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toDomain(RoleEntity entity);
}