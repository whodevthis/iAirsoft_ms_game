package org.project.msgame.infrastructure.persistence.repository;

import org.project.msgame.infrastructure.persistence.entities.GeofenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface GeofenceJpaRepository extends JpaRepository<GeofenceEntity, UUID>,
        JpaSpecificationExecutor<GeofenceEntity> {
}