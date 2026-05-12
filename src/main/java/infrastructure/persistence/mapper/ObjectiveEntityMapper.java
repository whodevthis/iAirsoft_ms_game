package infrastructure.persistence.mapper;

import domain.aggregates.Objective;
import domain.valueObjects.Marker;
import infrastructure.persistence.entities.ObjectiveEntity;

import infrastructure.persistence.mirrorClass.locations.MarkerJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ObjectiveEntityMapper {

    @Mapping(target = "marker", expression = "java(toMarkerJson(objective.getMarker()))")
    ObjectiveEntity toEntity(Objective objective);

    default Objective toDomain(ObjectiveEntity entity) {
        return new Objective(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage(),
                entity.getCode(),
                toMarker(entity.getMarker()),
                entity.isCompleted(),
                entity.getCompletedBy(),
                entity.getState()
        );
    }

    default MarkerJson toMarkerJson(Marker marker) {
        if (marker == null) return null;
        return new MarkerJson(marker.lat(), marker.lon(), marker.iconUrl());
    }

    default Marker toMarker(MarkerJson json) {
        if (json == null) return null;
        return new Marker(json.getLat(), json.getLon(), json.getIconUrl());
    }
}