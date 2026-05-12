package application.ports.in.location.query;

import application.dtos.location.LocationDetailsDto;
import application.dtos.location.LocationDto;

import java.util.List;

public interface SearchLocationUseCase {

     List<LocationDto> search(String data);

}
