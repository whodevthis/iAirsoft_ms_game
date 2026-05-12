package domain.aggregates;

import domain.valueObjects.Address;
import domain.valueObjects.Marker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Getter
@NoArgsConstructor
public class Location {

    private  UUID id;
    private UUID geofenceId;
    private Address address;
    private Marker marker;

    public Location( UUID id,UUID geofenceId,Address address,  Marker marker) {
        this.id = id;
        this.geofenceId = geofenceId;
        this.address = address;


        this.marker = marker;
    }

    public Location(UUID id, Address address, Marker marker) {
        this.id = id;
        this.address = address;
        this.marker = marker;

    }
}