package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Geofence;
import org.project.msgame.domain.valueObjects.GeoJson;
import org.project.msgame.infrastructure.persistence.entities.GeofenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeofenceEntityMapper {

    @Mapping(target = "geoJson", expression = "java(toGeoJsonString(geofence.getGeoJson()))")
    GeofenceEntity toEntity(Geofence geofence);

    default Geofence toDomain(GeofenceEntity entity) {
        if (entity == null) return null;
        return new Geofence(
                entity.getId(),
                entity.getName(),
                toGeoJson(entity.getGeoJson())
        );
    }

    default String toGeoJsonString(GeoJson geoJson) {
        if (geoJson == null) return null;
        return geoJson.value();
    }

    default GeoJson toGeoJson(String value) {
        if (value == null) return null;
        return new GeoJson(value);
    }
}