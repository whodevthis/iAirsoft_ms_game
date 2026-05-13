package org.project.msgame.infrastructure.persistence.repository;

import org.project.msgame.infrastructure.persistence.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface TeamJpaRepository extends JpaRepository<TeamEntity, UUID>,
        JpaSpecificationExecutor<TeamEntity> {
}