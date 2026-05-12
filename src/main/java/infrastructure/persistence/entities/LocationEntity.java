package infrastructure.persistence.entities;


import infrastructure.persistence.converters.locations.AddressConverter;
import infrastructure.persistence.converters.locations.MarkerConverter;
import infrastructure.persistence.mirrorClass.locations.AddressJson;
import infrastructure.persistence.mirrorClass.locations.MarkerJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID geofenceId;

    @Convert(converter = AddressConverter.class)
    @Column(columnDefinition = "json")
    private AddressJson address;

    @Convert(converter = MarkerConverter.class)
    @Column(columnDefinition = "json")
    private MarkerJson marker;
}
