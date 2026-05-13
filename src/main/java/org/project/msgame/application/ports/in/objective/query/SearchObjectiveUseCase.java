package org.project.msgame.application.ports.in.objective.query;

import org.project.msgame.application.dtos.objective.ObjectiveDto;


import java.util.List;

public interface SearchObjectiveUseCase {

     List<ObjectiveDto> search(String data);

}
