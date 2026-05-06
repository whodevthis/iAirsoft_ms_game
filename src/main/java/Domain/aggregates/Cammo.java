package Domain.aggregates;

import Domain.valueObjects.PatternCammo;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Cammo {

    private final UUID id;
    private String name;
    private PatternCammo pattern;

    public Cammo(UUID id, String name, PatternCammo pattern) {

        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name must not be blank");
        Assert.notNull(pattern, "pattern is null");

        this.id = id;
        this.name = name;
        this.pattern = pattern;
    }

    public void updateName(String name) {
        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name must not be blank");
        this.name = name;
    }

    public void updatePattern(PatternCammo pattern) {
        Assert.notNull(pattern, "pattern is null");
        this.pattern = pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cammo other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}