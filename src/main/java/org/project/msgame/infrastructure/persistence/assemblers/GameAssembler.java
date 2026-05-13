package org.project.msgame.infrastructure.persistence.assemblers;

import org.project.msgame.application.exceptions.EntityNotFoundException;
import org.project.msgame.domain.aggregates.Game;
import org.project.msgame.infrastructure.persistence.entities.GameEntity;
import org.project.msgame.infrastructure.persistence.entities.LocationEntity;
import org.project.msgame.infrastructure.persistence.entities.TeamEntity;
import org.project.msgame.infrastructure.persistence.repository.LocationJpaRepository;
import org.project.msgame.infrastructure.persistence.repository.TeamJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameAssembler {

    private final TeamJpaRepository teamJpaRepository;
    private final LocationJpaRepository locationJpaRepository;

    public GameEntity assembler(GameEntity entity, Game game) {

        List<TeamEntity> teams = teamJpaRepository.findAllById(game.getTeamIds());

        entity.setTeams(teams);

        LocationEntity location = locationJpaRepository.findById(game.getLocationId()).orElseThrow(() -> new EntityNotFoundException("location Not found"));

        entity.setLocation(location);

        return entity;

    }

}
