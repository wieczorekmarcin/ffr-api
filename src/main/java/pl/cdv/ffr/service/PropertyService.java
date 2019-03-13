package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.repository.PropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public List<Property> findAllProperties() {
        return propertyRepository.findAll();
    }

    public Property findPropertyById(Long id) {
        Optional<Property> byId = propertyRepository.findById(id);
        return byId.get();
    }
}
