package infrastructure.persistence.repository;

import infrastructure.persistence.entities.CammoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface CammoJpaRepository extends JpaRepository<CammoEntity, UUID>,
        JpaSpecificationExecutor<CammoEntity> {
}