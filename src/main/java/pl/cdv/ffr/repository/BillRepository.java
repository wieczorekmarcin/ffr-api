package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}