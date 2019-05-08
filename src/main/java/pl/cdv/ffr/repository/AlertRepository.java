package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
