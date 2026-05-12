package application.ports.in.objective.query;

import application.dtos.objective.ObjectiveDto;


import java.util.List;

public interface SearchObjectiveUseCase {

     List<ObjectiveDto> search(String data);

}
