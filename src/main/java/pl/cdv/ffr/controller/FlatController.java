package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.service.FlatService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlatController {

    @Autowired
    FlatService flatService;

    @RequestMapping(path = "/flats", method = RequestMethod.GET)
    public List<Flat> getAllFlats() {
        return flatService.findAllFlats();
    }

    @RequestMapping(path = "/flats/{id}", method = RequestMethod.GET)
    public Flat getFlat(@PathVariable("id") String id) {
        Long longId = Long.parseLong(id);
        return flatService.findFlatById(longId);
    }
}