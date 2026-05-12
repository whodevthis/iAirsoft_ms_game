package domain.aggregates;

import domain.states.ObjectiveState;
import domain.valueObjects.Marker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Objective {
    private  UUID id;
    private  String name;
    private  String description;
    private  String image;
    private String code;
    private  Marker marker;
    private boolean completed;
    private UUID completedBy;
    private ObjectiveState state;
}