package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Meter;
import pl.cdv.ffr.service.MeterService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MeterController {

    @Autowired
    MeterService meterService;

    @RequestMapping(path = "/meter", method = RequestMethod.GET)
    public String getLastMeterStatus(@RequestParam(value = "propertyId", required = true) Long propertyId, @RequestParam(value = "mediaId", required = true) Long mediaId) {
        return meterService.findLastMeterSatus(propertyId, mediaId);
    }

    @RequestMapping(path = "/meterHistory", method = RequestMethod.GET)
    public List<String> getMeterStatusHistory(@RequestParam(value = "propertyId", required = true) Long propertyId, @RequestParam(value = "mediaId", required = true) Long mediaId) {
        return meterService.findMeterStatusHistory(propertyId, mediaId);
    }

    @RequestMapping(path = "/meters", method = RequestMethod.GET)
    public List<Meter> getAllMeters() {
        return meterService.findAllMeters();
    }

    @RequestMapping(path = "/meters/{id}", method = RequestMethod.GET)
    public Meter getMeter(@PathVariable("id") String id) {
        return meterService.findMeterById(id);
    }

    @RequestMapping(path = "/meters", method = RequestMethod.POST)
    public Meter createMeter(@RequestBody Meter meter) {
        return meterService.createMeter(meter);
    }

    @RequestMapping(path = "/meters/{id}", method = RequestMethod.PUT)
    public Meter updateMeter(@RequestBody Meter meter, @PathVariable("id") String id) {
        return meterService.updateMeter(meter, id);
    }

    @RequestMapping(path = "/meters/{id}", method = RequestMethod.DELETE)
    public void deleteMeter(@PathVariable("id") String id) {
        meterService.deleteMeter(id);
    }
}