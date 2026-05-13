package org.project.msgame.infrastructure.persistence.repository;

import org.project.msgame.infrastructure.persistence.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, UUID>,
        JpaSpecificationExecutor<PlayerEntity> {
    boolean existsByUserId(UUID userId);
    Optional<PlayerEntity> findByUserId(UUID userId);
}
