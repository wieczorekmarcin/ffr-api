package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cdv.ffr.model.Tenat;

public interface TenatRepository extends JpaRepository<Tenat, Long> {
}
