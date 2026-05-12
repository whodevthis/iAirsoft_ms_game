package domain.service;

import domain.aggregates.Game;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDomainService {

    public void addTeam(Game game, UUID teamId) {
        if (game.getTeamIds() == null)
            throw new IllegalStateException("teamIds no inicializado");
        if (game.getTeamIds().contains(teamId))
            throw new IllegalArgumentException("El equipo ya está en el juego");
        if (game.getTeamIds().size() >= game.getMaxPlayers())
            throw new IllegalArgumentException("El juego está lleno");

        game.getTeamIds().add(teamId);
    }

    public void removeTeam(Game game, UUID teamId) {
        if (game.getTeamIds() == null || !game.getTeamIds().contains(teamId))
            throw new IllegalArgumentException("El equipo no está en el juego");

        game.getTeamIds().remove(teamId);
    }

}