package infrastructure.persistence.repository;

import infrastructure.persistence.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, UUID>,
        JpaSpecificationExecutor<PlayerEntity> {
}
