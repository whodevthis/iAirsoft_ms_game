package Application.dtos.game;

import Domain.states.GameStatus;

import java.util.UUID;

public record GameDto(UUID id , String name, String description, String imagePath, GameStatus status) {
}
