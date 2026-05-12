package infrastructure.persistence.repository;

import infrastructure.persistence.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID>,
        JpaSpecificationExecutor<LocationEntity> {
}