package application.ports.in.cammo.query;

import application.dtos.cammo.CammoDetailsDto;

import java.util.List;

public interface SearchCammoUseCase {

     List<CammoDetailsDto> search(String data);

}
