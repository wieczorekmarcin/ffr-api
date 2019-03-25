package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}