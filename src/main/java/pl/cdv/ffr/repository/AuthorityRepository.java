package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cdv.ffr.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
