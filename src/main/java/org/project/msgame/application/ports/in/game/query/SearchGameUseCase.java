package org.project.msgame.application.ports.in.game.query;

import org.project.msgame.application.dtos.game.GameDto;

import java.util.List;

public interface SearchGameUseCase {

     List<GameDto> search(String data);

}
