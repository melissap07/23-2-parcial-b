package co.edu.unisabana.parcialarquitectura.service.jpa;

import co.edu.unisabana.parcialarquitectura.repository.entity.CheckpointEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckpointRepository extends JpaRepository<CheckpointEntity, Integer> {

  Optional<CheckpointEntity> findFirstByDriverAndFacilityAndFinalizedIsFalse(String driver, String facility);

}
