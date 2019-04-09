package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Meter;
import pl.cdv.ffr.repository.MeterRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterService {

    @Autowired
    MeterRepository meterRepository;

    public List<Meter> findAllMeters() {
        return meterRepository.findAll();
    }

    public Meter findMeterById(String id) {
        Optional<Meter> byId = meterRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public String findLastMeterSatus(Long propertyId, Long mediaId) {
       return meterRepository.findAll()
               .stream()
               .filter(meter -> propertyId == meter.getProperty().getId())
               .filter(meter -> mediaId == meter.getMedia().getId())
               .sorted(Comparator.comparing(Meter::getId).reversed())
               .map(meter -> meter.getCurrentMeterStatus())
               .findFirst()
               .orElseThrow(() -> new RuntimeException("There's no meter with this propertyId and mediaId"));
    }

    public List<String> findMeterStatusHistory(Long propertyId, Long mediaId) {
        return meterRepository.findAll()
                .stream()
                .filter(meter -> propertyId == meter.getProperty().getId())
                .filter(meter -> mediaId == meter.getMedia().getId())
                .sorted(Comparator.comparing(Meter::getId).reversed())
                .map(meter -> meter.getCurrentMeterStatus())
                .collect(Collectors.toList());
    }

    public Meter createMeter(Meter meter) {
        return meterRepository.save(meter);
    }

    public Meter updateMeter(Meter newMeter, String id) {
        return meterRepository.findById(Long.parseLong(id))
                .map(meter -> {
                    meter.setCurrentMeterStatus(newMeter.getCurrentMeterStatus());
                    meter.setMedia(newMeter.getMedia());
                    meter.setProperty(newMeter.getProperty());
                    return meterRepository.save(meter);
                })
                .orElseGet(() -> {
                    newMeter.setId(Long.parseLong(id));
                    return meterRepository.save(newMeter);
                });
    }

    public void deleteMeter(String id) {
        meterRepository.deleteById(Long.parseLong(id));
    }
}