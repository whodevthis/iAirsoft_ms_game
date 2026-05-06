package Domain.InternEntities;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class TeamRole {

    private final UUID roleTemplateId;
    private final int quantity;
    private final String descriptionOverride;

    public TeamRole(UUID roleTemplateId, int quantity, String descriptionOverride) {
        Assert.notNull(roleTemplateId, "roleTemplateId is null");
        Assert.isTrue(quantity > 0, "quantity must be > 0");

        if (descriptionOverride != null) {
            Assert.hasText(descriptionOverride, "descriptionOverride must not be blank if provided");
        }

        this.roleTemplateId = roleTemplateId;
        this.quantity = quantity;
        this.descriptionOverride = descriptionOverride;
    }

    public static TeamRole withoutOverride(UUID roleTemplateId, int quantity) {
        return new TeamRole(roleTemplateId, quantity, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamRole other)) return false;
        return roleTemplateId.equals(other.roleTemplateId);
    }

    @Override
    public int hashCode() {
        return roleTemplateId.hashCode();
    }
}