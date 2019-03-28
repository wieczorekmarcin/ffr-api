package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.model.FlatStatus;
import pl.cdv.ffr.model.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}