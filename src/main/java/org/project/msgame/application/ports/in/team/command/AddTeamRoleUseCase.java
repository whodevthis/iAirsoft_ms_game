package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface AddTeamRoleUseCase {
    void addTeamRole(UUID teamId, UUID roleId, int quantity);
}

