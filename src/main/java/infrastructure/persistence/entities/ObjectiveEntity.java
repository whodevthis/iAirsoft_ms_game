package infrastructure.persistence.entities;

import domain.states.ObjectiveState;
import infrastructure.persistence.converters.locations.MarkerConverter;
import infrastructure.persistence.mirrorClass.locations.MarkerJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "objectives")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String image;

    @Column(unique = true)
    private String code;

    @Convert(converter = MarkerConverter.class)
    @Column(columnDefinition = "json")
    private MarkerJson marker;

    @Column(nullable = false)
    private boolean completed;

    @Column
    private UUID completedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ObjectiveState state;
}