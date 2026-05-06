package Domain.aggregates;

import Domain.valueObjects.Address;
import Domain.valueObjects.Marker;
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