package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.model.FlatStatus;
import pl.cdv.ffr.repository.FlatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlatService {

    @Autowired
    FlatRepository flatRepository;

    public List<Flat> findAllFlats() {
        return flatRepository.findAll();
    }

    public Flat findFlatById(String id) {
        Optional<Flat> byId = flatRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public List<Flat> findFlatsByStatus(FlatStatus flatStatus) {
        return flatRepository.findByFlatStatus(flatStatus);
    }

    public Flat createFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    public Flat updateFlat(Flat newFlat, String id) {
        return flatRepository.findById(Long.parseLong(id))
                .map(flat -> {
                    flat.setDescription(newFlat.getDescription());
                    flat.setFlatNumber(newFlat.getFlatNumber());
                    flat.setFlatStatus(newFlat.getFlatStatus());
                    flat.setImages(newFlat.getImages());
                    flat.setPricePerYard(newFlat.getPricePerYard());
                    flat.setTitle(newFlat.getTitle());
                    return flatRepository.save(flat);
                })
                .orElseGet(() -> {
                    newFlat.setId(Long.parseLong(id));
                    return flatRepository.save(newFlat);
                });
    }

    public void deleteFlat(String id) {
        flatRepository.deleteById(Long.parseLong(id));
    }
}