package Application.dtos.objective;

import Domain.states.ObjectiveState;
import Domain.valueObjects.Marker;

import java.util.UUID;

public record ObjectiveDetailsDto(UUID id, String name, String description, String image, Marker marker,
                                  boolean completed, UUID completedBy, ObjectiveState state) {
}
