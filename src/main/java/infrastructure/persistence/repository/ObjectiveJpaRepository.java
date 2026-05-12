package infrastructure.persistence.repository;

import infrastructure.persistence.entities.ObjectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface ObjectiveJpaRepository extends JpaRepository<ObjectiveEntity, UUID>,
        JpaSpecificationExecutor<ObjectiveEntity> {
}