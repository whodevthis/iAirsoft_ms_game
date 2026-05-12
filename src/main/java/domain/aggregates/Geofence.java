package domain.aggregates;

import domain.valueObjects.GeoJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Geofence {
    private UUID id;
    private String name;
    private GeoJson geoJson;
}