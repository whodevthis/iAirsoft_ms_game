
package Domain.aggregates;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Player {

    private final UUID id;
    private final UUID userId;
    private final UUID gameId;
    private final UUID teamId;
    private final UUID roleTemplateId;
    private String nickname;
    private String image;

    public Player(
            UUID id,
            UUID userId,
            UUID gameId,
            UUID teamId,
            UUID roleTemplateId,
            String nickname,
            String image) {


        Assert.notNull(userId, "userId is null");
        Assert.notNull(gameId, "gameId is null");
        Assert.notNull(teamId, "teamId is null");
        Assert.notNull(roleTemplateId, "roleTemplateId is null");
        Assert.notNull(nickname, "nickname is null");
        Assert.hasText(nickname, "nickname must not be blank");
        if (image != null) {
            Assert.hasText(image, "image must not be blank if provided");
        }

        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.teamId = teamId;
        this.roleTemplateId = roleTemplateId;
        this.nickname = nickname;
        this.image = image;
    }

    public void updateNickname(String nickname) {
        Assert.notNull(nickname, "nickname is null");
        Assert.hasText(nickname, "nickname must not be blank");
        this.nickname = nickname;
    }

    public void updateImage(String image) {
        if (image != null) {
            Assert.hasText(image, "image must not be blank if provided");
        }
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}