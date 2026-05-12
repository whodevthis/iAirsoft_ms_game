package infrastructure.persistence.repository;

import infrastructure.persistence.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface GameJpaRepository extends JpaRepository<GameEntity, UUID>,
        JpaSpecificationExecutor<GameEntity> {
}