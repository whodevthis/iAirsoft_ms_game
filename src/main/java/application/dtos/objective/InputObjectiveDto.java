package application.dtos.objective;

import domain.states.ObjectiveState;

public record InputObjectiveDto(String name, String description, String image, double lat, double lon,
                               String code) {
}
