package Domain.aggregates;

import Domain.valueObjects.Address;
import Domain.valueObjects.Marker;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Location {

    private final UUID id;
    private UUID geofenceId;
    private Address address;
    private Marker marker;

    public Location(UUID id, UUID geofenceId, Address address, Marker marker) {
        Assert.notNull(id, "id is null");
        Assert.notNull(geofenceId, "geofenceId is null");
        Assert.notNull(address, "address is null");
        Assert.notNull(marker, "marker is null");

        this.id = id;
        this.geofenceId = geofenceId;
        this.address = address;
        this.marker = marker;
    }

    // FIX 3: recibe UUID, no Geofence — el dominio solo guarda la referencia
    public void updateGeofence(UUID geofenceId) {
        Assert.notNull(geofenceId, "geofenceId is null");
        this.geofenceId = geofenceId;
    }

    public void updateAddress(Address address) {
        Assert.notNull(address, "address is null");
        this.address = address;
    }


    public void updateMarker(Marker marker) {
        Assert.notNull(marker, "marker is null");
        this.marker = marker;
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