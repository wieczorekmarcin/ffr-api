package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Offer;
import pl.cdv.ffr.service.OfferService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OfferController {

    @Autowired
    OfferService offerService;

    @RequestMapping(path = "/offers", method = RequestMethod.GET)
    public List<Offer> getAllOffers() {
        return offerService.findAllOffers();
    }

    @RequestMapping(path = "/offers/{id}", method = RequestMethod.GET)
    public Offer getOffer(@PathVariable("id") String id) {
        return offerService.findFlatById(id);
    }

    @RequestMapping(path = "/offers", method = RequestMethod.POST)
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @RequestMapping(path = "/offers/{id}", method = RequestMethod.PUT)
    public Offer updateOffer(@RequestBody Offer offer, @PathVariable("id") String id) {
        return offerService.updateOffer(offer, id);
    }

    @RequestMapping(path = "/offers/{id}", method = RequestMethod.DELETE)
    public void deleteOffer(@PathVariable("id") String id) {
        offerService.deleteOffer(id);
    }
}