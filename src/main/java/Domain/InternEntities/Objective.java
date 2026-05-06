package Domain.InternEntities;

import Domain.states.ObjectiveState;
import Domain.valueObjects.Marker;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
public class Objective {

    private final UUID id;
    private final String name;
    private final String description;
    private final String image;
    private final Marker marker;
    private boolean completed;
    private UUID completedBy;
    private ObjectiveState state;

    public Objective(
            UUID id,
            String name,
            String description,
            String image,
            Marker marker) {

        Assert.notNull(id, "id is null");
        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name must not be blank");
        Assert.notNull(description, "description is null");
        Assert.hasText(description, "description must not be blank");
        Assert.notNull(marker, "marker is null");
        if (image != null) {
            Assert.hasText(image, "image must not be blank if provided");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.marker = marker;
        this.completed = false;
        this.completedBy = null;
        this.state = ObjectiveState.CREATED;
    }

    public void activate() {

        Assert.state(state == ObjectiveState.CREATED, "Only CREATED objectives can be activated");
        this.state = ObjectiveState.ON_COURSE;
    }

    public void complete(UUID playerId) {
        Assert.notNull(playerId, "playerId is null");

        Assert.state(state == ObjectiveState.ON_COURSE, "Objective must be ON_COURSE to be completed");
        Assert.state(!completed, "Objective is already completed");

        this.completed = true;
        this.completedBy = playerId;

        this.state = ObjectiveState.END;
    }

    public void cancel() {

        Assert.state(state != ObjectiveState.END, "Cannot cancel a completed objective");
        this.state = ObjectiveState.CANCELLED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Objective other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}