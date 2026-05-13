package org.project.msgame.domain.valueObjects;

import java.util.UUID;

public record TeamRole(UUID role, int quantity) {
    public TeamRole {
        if (role == null)
            throw new IllegalArgumentException("player must not be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("quantity must be > 0");
    }
}