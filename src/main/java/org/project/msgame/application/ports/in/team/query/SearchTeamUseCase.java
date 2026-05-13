package org.project.msgame.application.ports.in.team.query;

import org.project.msgame.application.dtos.team.TeamDto;

import java.util.List;

public interface SearchTeamUseCase {

     List<TeamDto> search(String data);

}
