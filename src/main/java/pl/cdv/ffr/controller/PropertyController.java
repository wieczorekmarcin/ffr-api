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
        return propertyService.findPropertyById(id);
    }

    @RequestMapping(path = "/properties", method = RequestMethod.POST)
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.PUT)
    public Property updateProperty(@RequestBody Property property, @PathVariable("id") String id) {
        return propertyService.updateProperty(property, id);
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.DELETE)
    public void deleteProperty(@PathVariable("id") String id) {
        propertyService.deleteProperty(id);
    }
}