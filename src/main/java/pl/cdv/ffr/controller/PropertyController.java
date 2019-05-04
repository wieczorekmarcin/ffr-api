package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.model.PropertyStatus;
import pl.cdv.ffr.service.PropertyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @RequestMapping(path = "/properties", method = RequestMethod.GET)
    public List<Property> getAllPropertiesForRentier(HttpServletRequest request, @RequestParam(value = "status", required = false) PropertyStatus propertyStatus) {
        if (propertyStatus != null) {
            return propertyService.findPropertysByStatus(request, propertyStatus);
        } else {
            return propertyService.findAllPropertys(request);
        }
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.GET)
    public Property getPropertyForRentier(HttpServletRequest request, @PathVariable("id") String id) {
        return propertyService.findPropertyById(request, id);
    }

    @RequestMapping(path = "/properties", method = RequestMethod.POST)
    public Property createPropertyByRetier(HttpServletRequest request, @RequestBody Property property) {
        return propertyService.createProperty(request, property);
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.PUT)
    public Property updatePropertyByRentier(HttpServletRequest request, @RequestBody Property property, @PathVariable("id") String id) {
        return propertyService.updateProperty(request, property, id);
    }

    @RequestMapping(path = "/properties/{id}", method = RequestMethod.DELETE)
    public void deletePropertyByRentier(HttpServletRequest request, @PathVariable("id") String id) {
        propertyService.deleteProperty(request, id);
    }
}