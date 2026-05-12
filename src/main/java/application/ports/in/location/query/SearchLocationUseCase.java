package application.ports.in.location.query;

import application.dtos.location.LocationDetailsDto;

import java.util.List;

public interface SearchLocationUseCase {

     List<LocationDetailsDto> search(String data);

}
