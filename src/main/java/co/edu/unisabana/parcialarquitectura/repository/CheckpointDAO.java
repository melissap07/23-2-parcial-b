package co.edu.unisabana.parcialarquitectura.repository;

import co.edu.unisabana.parcialarquitectura.repository.entity.CheckpointEntity;
import co.edu.unisabana.parcialarquitectura.service.jpa.CheckpointRepository;
import co.edu.unisabana.parcialarquitectura.service.model.Checkin;
import co.edu.unisabana.parcialarquitectura.service.model.Checkout;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CheckpointDAO implements CheckpointPort {

  private CheckpointRepository checkpointRepository;


  @Override
  public void saveCheckout(Checkout checkout) {
    checkpointRepository.save(CheckpointEntity.fromCheckout(checkout));
  }

  @Override
  public Checkin findLastCheckin(String driver, String facility) {
    Optional<CheckpointEntity> result = checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse(driver,
        facility);
    return result.map(CheckpointEntity::toCheckin).orElse(null);
  }

  @Override
  public void finishCheckin(Checkin checkin) {
    CheckpointEntity checkpoint = checkpointRepository.findById(checkin.getId()).get();
    checkpoint.setFinalized(true);
    checkpointRepository.save(checkpoint);
  }

}