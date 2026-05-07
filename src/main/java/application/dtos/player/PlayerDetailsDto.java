package application.dtos.player;

import java.util.UUID;

public record PlayerDetailsDto(UUID id, UUID userId, String nickname, String imagePath) {
}
