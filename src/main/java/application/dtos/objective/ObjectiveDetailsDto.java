package application.dtos.objective;

import domain.states.ObjectiveState;
import domain.valueObjects.Marker;

import java.util.UUID;

public record ObjectiveDetailsDto(UUID id, String name, String description, String image, Marker marker,String code,
                                  boolean completed, UUID completedBy, ObjectiveState state) {
}
