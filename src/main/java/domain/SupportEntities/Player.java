package domain.SupportEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private  UUID id;
    private  UUID userId;
    private String nickname;
    private String imagePath;
}