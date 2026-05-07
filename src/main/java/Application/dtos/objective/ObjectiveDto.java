package Application.dtos.objective;

import java.util.UUID;

public record ObjectiveDto(UUID id, String name, String description) {
}
