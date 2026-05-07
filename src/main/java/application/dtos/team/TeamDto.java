package application.dtos.team;

import java.util.UUID;

public record TeamDto(UUID id, String name, String description) {
}
