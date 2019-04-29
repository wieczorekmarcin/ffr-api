package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Tenat;
import pl.cdv.ffr.service.TenatService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TenatController {

    @Autowired
    TenatService tenatService;

    @RequestMapping(path = "/tenats", method = RequestMethod.GET)
    public List<Tenat> getAllTenats() {
        return tenatService.findAllTenats();
    }

    @RequestMapping(path = "/tenats/{id}", method = RequestMethod.GET)
    public Tenat getTenat(@PathVariable("id") String id) {
        return tenatService.findTenatById(id);
    }

    @RequestMapping(path = "/tenats", method = RequestMethod.POST)
    public Tenat createTenat(@RequestBody Tenat property) {
        return tenatService.createTenat(property);
    }

    @RequestMapping(path = "/tenats/{id}", method = RequestMethod.PUT)
    public Tenat updateTenat(@RequestBody Tenat property, @PathVariable("id") String id) {
        return tenatService.updateTenat(property, id);
    }

    @RequestMapping(path = "/tenats/{id}", method = RequestMethod.DELETE)
    public void deleteTenat(@PathVariable("id") String id) {
        tenatService.deleteTenat(id);
    }
}
