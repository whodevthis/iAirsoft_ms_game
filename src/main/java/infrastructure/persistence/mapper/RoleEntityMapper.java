package infrastructure.persistence.mapper;

import domain.aggregates.Role;
import infrastructure.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toDomain(RoleEntity entity);
}