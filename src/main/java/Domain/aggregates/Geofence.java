package Domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Geofence {
    private  UUID id;
    private String name;
    private String geoJson;

}