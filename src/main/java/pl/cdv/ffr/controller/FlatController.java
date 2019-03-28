package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.model.FlatStatus;
import pl.cdv.ffr.service.FlatService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlatController {

    @Autowired
    FlatService flatService;

    @RequestMapping(path = "/flats", method = RequestMethod.GET)
    public List<Flat> getAllFlatsWithParams(@RequestParam(value = "status", required = false) FlatStatus flatStatus) {
        if (flatStatus != null) {
            return flatService.findFlatsByStatus(flatStatus);
        } else {
            return flatService.findAllFlats();
        }
    }

    @RequestMapping(path = "/flats/{id}", method = RequestMethod.GET)
    public Flat getFlat(@PathVariable("id") String id) {
        return flatService.findFlatById(id);
    }

    @RequestMapping(path = "/flats", method = RequestMethod.POST)
    public Flat createFlat(@RequestBody Flat flat) {
        return flatService.createFlat(flat);
    }

    @RequestMapping(path = "/flats/{id}", method = RequestMethod.PUT)
    public Flat updateFlat(@RequestBody Flat flat, @PathVariable("id") String id) {
        return flatService.updateFlat(flat, id);
    }

    @RequestMapping(path = "/flats/{id}", method = RequestMethod.DELETE)
    public void deleteFlat(@PathVariable("id") String id) {
        flatService.deleteFlat(id);
    }
}