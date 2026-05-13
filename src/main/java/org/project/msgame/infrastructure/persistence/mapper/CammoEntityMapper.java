package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Cammo;
import org.project.msgame.infrastructure.persistence.entities.CammoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CammoEntityMapper {
    CammoEntity toEntity(Cammo cammo);
    Cammo toDomain(CammoEntity entity);
}