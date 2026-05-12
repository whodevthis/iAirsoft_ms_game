package infrastructure.persistence.repository;

import infrastructure.persistence.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface TeamJpaRepository extends JpaRepository<TeamEntity, UUID>,
        JpaSpecificationExecutor<TeamEntity> {
}