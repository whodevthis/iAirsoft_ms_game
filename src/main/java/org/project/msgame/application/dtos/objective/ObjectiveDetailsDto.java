package org.project.msgame.application.dtos.objective;

import org.project.msgame.domain.states.ObjectiveState;
import org.project.msgame.domain.valueObjects.Marker;

import java.util.UUID;

public record ObjectiveDetailsDto(UUID id, String name, String description, String image, Marker marker,String code,
                                  boolean completed, UUID completedBy, ObjectiveState state) {
}
