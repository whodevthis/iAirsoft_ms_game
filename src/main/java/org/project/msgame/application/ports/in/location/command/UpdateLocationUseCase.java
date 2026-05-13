package org.project.msgame.application.ports.in.location.command;

import org.project.msgame.application.dtos.location.LocationDetailsDto;

import java.util.UUID;

public interface UpdateLocationUseCase {
    UUID update(LocationDetailsDto locationDetailsDto);
}