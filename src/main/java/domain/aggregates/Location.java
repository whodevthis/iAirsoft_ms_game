package domain.aggregates;

import domain.valueObjects.Address;
import domain.valueObjects.Marker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private  UUID id;
    private UUID geofenceId;
    private Address address;
    private Marker marker;

}