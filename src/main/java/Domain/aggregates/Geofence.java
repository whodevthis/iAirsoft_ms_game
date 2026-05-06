package Domain.aggregates;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Geofence {

    private final UUID id;
    private String name;
    private String geoJson;

    public Geofence(UUID id, String name, String geoJson) {
        Assert.notNull(id, "id is null");
        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name must not be blank");
        Assert.notNull(geoJson, "geoJson is null");
        Assert.hasText(geoJson, "geoJson must not be blank");

        this.id = id;
        this.name = name;
        this.geoJson = geoJson;
    }

    public void updateName(String name) {
        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name must not be blank");
        this.name = name;
    }

    public void updateGeoJson(String geoJson) {
        Assert.notNull(geoJson, "geoJson is null");
        Assert.hasText(geoJson, "geoJson must not be blank");
        this.geoJson = geoJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geofence other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}