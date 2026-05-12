package infrastructure.persistence.mirrorClass.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRoleJson {
    private UUID playerId;
    private UUID roleId;
}
