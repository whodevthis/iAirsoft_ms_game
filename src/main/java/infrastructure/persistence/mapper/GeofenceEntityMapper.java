package infrastructure.persistence.mapper;

import domain.aggregates.Geofence;
import domain.valueObjects.GeoJson;
import infrastructure.persistence.entities.GeofenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeofenceEntityMapper {

    @Mapping(target = "geoJson", expression = "java(toGeoJsonString(geofence.getGeoJson()))")
    GeofenceEntity toEntity(Geofence geofence);

    @Mapping(target = "geoJson", expression = "java(toGeoJson(entity.getGeoJson()))")
    Geofence toDomain(GeofenceEntity entity);

    default String toGeoJsonString(GeoJson geoJson) {
        if (geoJson == null) return null;
        return geoJson.value();
    }

    default GeoJson toGeoJson(String value) {
        if (value == null) return null;
        return new GeoJson(value);
    }
}