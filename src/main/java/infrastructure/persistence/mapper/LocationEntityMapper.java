package infrastructure.persistence.mapper;

import domain.aggregates.Location;
import domain.valueObjects.Address;
import domain.valueObjects.Marker;
import infrastructure.persistence.entities.LocationEntity;

import infrastructure.persistence.mirrorClass.locations.AddressJson;
import infrastructure.persistence.mirrorClass.locations.MarkerJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationEntityMapper {

    @Mapping(target = "marker", expression = "java(toMarkerJson(location.getMarker()))")
    @Mapping(target = "address", expression = "java(toAddressJson(location.getAddress()))")
    LocationEntity toEntity(Location location);

    @Mapping(target = "marker", expression = "java(toMarker(entity.getMarker()))")
    @Mapping(target = "address", expression = "java(toAddress(entity.getAddress()))")
    Location toDomain(LocationEntity entity);

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
        return new AddressJson(
                address.street(),
                address.city(),
                address.cp(),
                address.province(),
                address.country());
    }

    default Address toAddress(AddressJson json) {
        if (json == null) return null;
        return new Address(
                json.getStreet(),
                json.getCity(),
                json.getCp(),
                json.getProvince(),
                json.getCountry());
    }
}