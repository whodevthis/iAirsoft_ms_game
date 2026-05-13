package org.project.msgame.application.ports.in.team.command;

import java.util.UUID;

public interface RemoveTeamRoleUseCase {
    void removeTeamRole(UUID teamId, UUID roleId);
}
