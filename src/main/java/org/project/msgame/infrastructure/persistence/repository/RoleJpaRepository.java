package org.project.msgame.infrastructure.persistence.repository;

import org.project.msgame.infrastructure.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, UUID>,
        JpaSpecificationExecutor<RoleEntity> {
}