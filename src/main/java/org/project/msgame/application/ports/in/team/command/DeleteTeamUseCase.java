package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface DeleteTeamUseCase {
    void delete(UUID id);
}
