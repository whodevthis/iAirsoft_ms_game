package infrastructure.persistence.mapper;

import domain.aggregates.Cammo;
import infrastructure.persistence.entities.CammoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CammoEntityMapper {
    CammoEntity toEntity(Cammo cammo);
    Cammo toDomain(CammoEntity entity);
}