package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
}
