package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Offer;
import pl.cdv.ffr.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public List<Offer> findAllOffers() {
        return offerRepository.findAll();
    }

    public Offer findFlatById(String id) {
        Optional<Offer> byId = offerRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Offer newOffer, String id) {
        return offerRepository.findById(Long.parseLong(id))
                .map(offer -> {
                    offer.setId(newOffer.getId());
                    offer.setTitle(newOffer.getTitle());
                    offer.setShortDescription(newOffer.getShortDescription());
                    offer.setDescription(newOffer.getDescription());
                    offer.setFlat(newOffer.getFlat());
                    return offerRepository.save(offer);
                })
                .orElseGet(() -> {
                    newOffer.setId(Long.parseLong(id));
                    return offerRepository.save(newOffer);
                });
    }

    public void deleteOffer(String id) {
        offerRepository.deleteById(Long.parseLong(id));
    }
}