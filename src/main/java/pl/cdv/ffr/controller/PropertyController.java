package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.service.PropertyService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @RequestMapping(path = "/properties", method = RequestMethod.GET)
    public List<Property> getAllProperties() {
        return propertyService.findAllProperties();
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.GET)
    public Property getProperty(@PathVariable("id") String id) {
        Long longId = Long.parseLong(id);
        return propertyService.findPropertyById(longId);
    }
}