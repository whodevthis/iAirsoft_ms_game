package Domain.aggregates;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Role {

    private final UUID id;
    private final UUID roleTemplateId;
    private final UUID gameId;
    private String description;
    private Map<String, Object> characteristics;

    public Role(
            UUID id,
            UUID roleTemplateId,
            UUID gameId,
            String description,
            Map<String, Object> characteristics) {

        Assert.notNull(id, "id is null");
        Assert.notNull(roleTemplateId, "roleTemplateId is null");
        Assert.notNull(gameId, "gameId is null");
        Assert.notNull(description, "description is null");
        Assert.hasText(description, "description must not be blank");

        this.id = id;
        this.roleTemplateId = roleTemplateId;
        this.gameId = gameId;
        this.description = description;
        this.characteristics = characteristics != null
                ? new HashMap<>(characteristics)
                : new HashMap<>();
    }

    public void updateDescription(String description) {
        Assert.notNull(description, "description is null");
        Assert.hasText(description, "description must not be blank");
        this.description = description;
    }

    public void putCharacteristic(String key, Object value) {
        Assert.hasText(key, "key must not be blank");
        Assert.notNull(value, "value is null");
        this.characteristics.put(key, value);
    }

    public void removeCharacteristic(String key) {
        Assert.hasText(key, "key must not be blank");
        this.characteristics.remove(key);
    }

    public Map<String, Object> getCharacteristics() {
        return Map.copyOf(characteristics); // inmutable hacia fuera
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}