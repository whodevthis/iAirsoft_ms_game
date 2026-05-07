package Application.dtos.objective;

import Domain.states.ObjectiveState;

public record InputObjectiveDto(String name, String description, String image, double lat, double lon,
                                boolean completed, ObjectiveState state) {
}
