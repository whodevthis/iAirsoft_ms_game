package Application.dtos.player;

import java.util.UUID;

public record InputPlayerDto (UUID userId,
         String nickname,
         String imagePath

){
}
