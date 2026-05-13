package org.project.msgame.infrastructure.persistence.entities;


import org.project.msgame.infrastructure.persistence.converters.locations.AddressConverter;
import org.project.msgame.infrastructure.persistence.converters.locations.MarkerConverter;
import org.project.msgame.infrastructure.persistence.mirrorClasses.locations.AddressJson;
import org.project.msgame.infrastructure.persistence.mirrorClasses.locations.MarkerJson;
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


    @ManyToOne
    @JoinColumn(name = "geofence_id")
    private GeofenceEntity geofence;

    @Convert(converter = AddressConverter.class)
    @Column(columnDefinition = "json")
    private AddressJson address;

    @Convert(converter = MarkerConverter.class)
    @Column(columnDefinition = "json")
    private MarkerJson marker;
}
