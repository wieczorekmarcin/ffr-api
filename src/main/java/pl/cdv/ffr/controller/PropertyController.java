package pl.cdv.ffr.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.model.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropertyController {

    private static final List<Property> properties;

    static {
        properties = new ArrayList<>();
        properties.add(new Property(new Long(1), "ul. Kwiatowa", "32", "Poznań", "502m2",
                Arrays.asList(
                    new Flat(new Long(1), "12"),
                    new Flat(new Long(2), "13"),
                    new Flat(new Long(3), "14")
                )));
        properties.add(new Property(new Long(2), "ul. Testowa", "18", "Luboń", "612m2",
                Arrays.asList(
                    new Flat(new Long(4), "3"),
                    new Flat(new Long(5), "4"),
                    new Flat(new Long(6), "5")
                )));
    }

    @RequestMapping(path = "/property", method = RequestMethod.GET)
    public List<Property> getProperty() {
        return properties;
    }

    @RequestMapping(path = "/property/{id}", method = RequestMethod.GET)
    public Property getProperty(@PathVariable("id") String id) {
        Long longId = Long.parseLong(id);
        return properties.stream()
                .filter(property -> longId.equals(property.getId()))
                .findAny().orElse(null);
    }
}
