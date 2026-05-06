
package Domain.InternEntities;

import Domain.aggregates.Geofence;
import Domain.valueObjects.Marker;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Respawn {

    private final UUID id;
    private final Marker marker;
    private final Geofence geofence;

    public Respawn(UUID id, Marker marker, Geofence geofence) {
        Assert.notNull(id, "id is null");
        Assert.notNull(marker, "marker is null");
        Assert.notNull(geofence, "geofence is null");


        this.id = id;
        this.marker = marker;
        this.geofence = geofence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Respawn other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}