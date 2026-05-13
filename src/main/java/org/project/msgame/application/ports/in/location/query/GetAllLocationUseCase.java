package org.project.msgame.application.ports.in.location.query;

import org.project.msgame.application.dtos.location.LocationDto;

import java.util.List;

public interface GetAllLocationUseCase {
    List<LocationDto>getAll();
}
