package Domain.aggregates;

import Domain.valueObjects.Address;
import Domain.valueObjects.Coordinates;
import Domain.valueObjects.Geofence;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Location {

    private final UUID id;
    private Geofence geofence;
    private Address address;
    private Coordinates coordinates;

    public Location(UUID id, Geofence geofence, Address address, Coordinates coordinates) {
        Assert.notNull(id, "id is null");
        Assert.notNull(geofence, "geofence is null");
        Assert.notNull(address, "address is null");
        Assert.notNull(coordinates, "coordinates is null");
        Assert.isTrue(geofence.contains(coordinates), "coordinates must be inside the geofence");

        this.id = id;
        this.geofence = geofence;
        this.address = address;
        this.coordinates = coordinates;
    }

    public void updateGeofence(Geofence geofence) {
        Assert.notNull(geofence, "geofence is null");
        Assert.isTrue(geofence.contains(coordinates), "coordinates must remain inside the new geofence");
        this.geofence = geofence;
    }

    public void updateAddress(Address address) {
        Assert.notNull(address, "address is null");
        this.address = address;
    }

    public void updateCoordinates(Coordinates coordinates) {
        Assert.notNull(coordinates, "coordinates is null");
        Assert.isTrue(geofence.contains(coordinates), "new coordinates must be inside the geofence");
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}