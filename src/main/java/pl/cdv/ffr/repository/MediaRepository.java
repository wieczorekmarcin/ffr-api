package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
