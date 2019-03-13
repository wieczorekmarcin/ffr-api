package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
