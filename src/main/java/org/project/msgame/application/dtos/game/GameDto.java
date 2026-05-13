package org.project.msgame.application.dtos.game;

import org.project.msgame.domain.states.GameStatus;

import java.util.UUID;

public record GameDto(UUID id , String name, String description, String imagePath, GameStatus status) {
}
