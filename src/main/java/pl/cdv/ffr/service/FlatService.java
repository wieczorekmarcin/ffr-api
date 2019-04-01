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
                    flat.setFlatStatus(newFlat.getFlatStatus());
                    flat.setTitle(newFlat.getTitle());
                    flat.setImages(newFlat.getImages());
                    flat.setAvailableFrom(newFlat.getAvailableFrom());
                    flat.setBail(newFlat.getBail());
                    flat.setBuildingMaterial(newFlat.getBuildingMaterial());
                    flat.setBuildingType(newFlat.getBuildingType());
                    flat.setCity(newFlat.getCity());
                    flat.setFloor(newFlat.getFloor());
                    flat.setFloorsNumber(newFlat.getFloorsNumber());
                    flat.setHeating(newFlat.getHeating());
                    flat.setWindows(newFlat.getWindows());
                    flat.setPostCode(newFlat.getPostCode());
                    flat.setPrice(newFlat.getPrice());
                    flat.setRoomsNumber(newFlat.getRoomsNumber());
                    flat.setStreet(newFlat.getStreet());
                    flat.setSurface(newFlat.getSurface());
                    flat.setAdditionalInformation(newFlat.getAdditionalInformation());
                    flat.setEquipment(newFlat.getEquipment());
                    flat.setMedia(newFlat.getMedia());
                    flat.setSecurity(newFlat.getSecurity());
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