package application.dtos.team;

import java.util.UUID;

public record InputTeamDto(UUID gameId, String name, String description, String imagePath,
                           UUID role, int quantityRole,
                           double latRespawn, double lonRespawn

                           ) {
}
