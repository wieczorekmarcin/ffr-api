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

    public Property findPropertyById(String id) {
        Optional<Property> byId = propertyRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Property newProperty, String id) {
        return propertyRepository.findById(Long.parseLong(id))
                .map(property -> {
                    property.setCity(newProperty.getCity());
                    property.setFlats(newProperty.getFlats());
                    property.setStreet(newProperty.getStreet());
                    property.setStreetNumber(newProperty.getStreetNumber());
                    property.setYardage(newProperty.getYardage());
                    return propertyRepository.save(property);
                })
                .orElseGet(() -> {
                    newProperty.setId(Long.parseLong(id));
                    return propertyRepository.save(newProperty);
                });
    }

    public void deleteProperty(String id) {
        propertyRepository.deleteById(Long.parseLong(id));
    }
}
