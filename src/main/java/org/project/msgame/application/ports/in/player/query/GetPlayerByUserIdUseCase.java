package org.project.msgame.application.ports.in.player.query;

import org.project.msgame.application.dtos.player.PlayerDetailsDto;
import java.util.UUID;

public interface GetPlayerByUserIdUseCase {
    PlayerDetailsDto getByUserId(UUID userId);
}