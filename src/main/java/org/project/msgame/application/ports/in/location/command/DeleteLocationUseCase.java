package org.project.msgame.application.ports.in.location.command;

import java.util.UUID;

public interface DeleteLocationUseCase {
    void delete (UUID id);
}
