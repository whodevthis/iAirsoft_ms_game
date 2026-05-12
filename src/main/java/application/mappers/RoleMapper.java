package application.mappers;

import application.dtos.role.InputRoleDto;
import application.dtos.role.RoleDetailsDto;

import domain.aggregates.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", ignore = true)
    Role toDomain(InputRoleDto dto);

    @Mapping(target = "imagePath", ignore = true)
    RoleDetailsDto toDetailsDTO(Role domain);
}