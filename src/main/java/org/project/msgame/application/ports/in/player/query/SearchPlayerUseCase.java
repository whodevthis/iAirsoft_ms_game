package org.project.msgame.application.ports.in.player.query;

import org.project.msgame.application.dtos.player.PlayerDetailsDto;

import java.util.List;

public interface SearchPlayerUseCase {

    List<PlayerDetailsDto> search(String data);

}
