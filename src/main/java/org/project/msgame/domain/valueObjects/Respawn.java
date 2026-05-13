package org.project.msgame.domain.valueObjects;

public record Respawn(Marker marker) {
    public Respawn {
        if (marker == null)
            throw new IllegalArgumentException("marker must not be null");
    }
}