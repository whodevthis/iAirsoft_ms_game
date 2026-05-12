package infrastructure.persistence.mirrorClass.locations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkerJson {
    private Double lat;
    private Double lon;
    private String iconUrl;
}