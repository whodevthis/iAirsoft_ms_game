package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Location;
import org.project.msgame.domain.valueObjects.Address;
import org.project.msgame.domain.valueObjects.Marker;
import org.project.msgame.infrastructure.persistence.entities.LocationEntity;

import org.project.msgame.infrastructure.persistence.mirrorClasses.locations.AddressJson;
import org.project.msgame.infrastructure.persistence.mirrorClasses.locations.MarkerJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationEntityMapper {

    @Mapping(target = "marker", expression = "java(toMarkerJson(location.getMarker()))")
    @Mapping(target = "address", expression = "java(toAddressJson(location.getAddress()))")
    LocationEntity toEntity(Location location);

    default Location toDomain(LocationEntity entity) {
        if (entity == null) {
            return null;
        }
        Location location = new Location(entity.getId(), entity.getGeofence().getId(), toAddress(entity.getAddress()), toMarker(entity.getMarker()));

        return location;
    }


    default MarkerJson toMarkerJson(Marker marker) {
        if (marker == null) return null;
        return new MarkerJson(marker.lat(), marker.lon(), marker.iconUrl());
    }

    default Marker toMarker(MarkerJson json) {
        if (json == null) return null;
        return new Marker(json.getLat(), json.getLon(), json.getIconUrl());
    }

    default AddressJson toAddressJson(Address address) {
        if (address == null) return null;
        return new AddressJson(address.street(), address.city(), address.cp(), address.province(), address.country());
    }

    default Address toAddress(AddressJson json) {
        if (json == null) return null;
        return new Address(json.getStreet(), json.getCity(), json.getCp(), json.getProvince(), json.getCountry());
    }
}