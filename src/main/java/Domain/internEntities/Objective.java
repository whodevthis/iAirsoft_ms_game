package Domain.internEntities;

import Domain.states.ObjectiveState;
import Domain.valueObjects.Marker;
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
    private  Marker marker;
    private boolean completed;
    private UUID completedBy;
    private ObjectiveState state;
}