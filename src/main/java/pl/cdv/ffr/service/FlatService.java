package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Flat;
import pl.cdv.ffr.model.FlatStatus;
import pl.cdv.ffr.repository.FlatRepository;
import pl.cdv.ffr.utils.ftp.FTPFileWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FlatService {

    @Autowired
    FlatRepository flatRepository;

    @Autowired
    FTPFileWriter ftpFileWriter;

    public List<Flat> findAllFlats() {
        return flatRepository.findAll();
    }

    public Flat findFlatById(String id) {
        Optional<Flat> byId = flatRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public List<Flat> findFlatsByStatus(FlatStatus flatStatus) {
        return flatRepository.findByFlatStatus(flatStatus);
    }

    public Flat createFlat(Flat flat) {
        if(flat.getImages() != null && !flat.getImages().isEmpty()) {
            List<String> urls = getImagesUrls(flat.getImages());
            flat.setImagesUrls(urls);
        }
        return flatRepository.save(flat);
    }

    public Flat updateFlat(Flat newFlat, String id) {
        return flatRepository.findById(Long.parseLong(id))
                .map(flat -> {
                    flat.setDescription(newFlat.getDescription());
                    flat.setFlatStatus(newFlat.getFlatStatus());
                    flat.setTitle(newFlat.getTitle());
                    flat.setImagesUrls(getImagesUrls(newFlat.getImages()));
                    flat.setAvailableFrom(newFlat.getAvailableFrom());
                    flat.setBail(newFlat.getBail());
                    flat.setBuildingMaterial(newFlat.getBuildingMaterial());
                    flat.setBuildingType(newFlat.getBuildingType());
                    flat.setCity(newFlat.getCity());
                    flat.setFloor(newFlat.getFloor());
                    flat.setFloorsNumber(newFlat.getFloorsNumber());
                    flat.setHeating(newFlat.getHeating());
                    flat.setWindows(newFlat.getWindows());
                    flat.setPostCode(newFlat.getPostCode());
                    flat.setPrice(newFlat.getPrice());
                    flat.setRoomsNumber(newFlat.getRoomsNumber());
                    flat.setStreet(newFlat.getStreet());
                    flat.setSurface(newFlat.getSurface());
                    flat.setAdditionalInformation(newFlat.getAdditionalInformation());
                    flat.setEquipment(newFlat.getEquipment());
                    flat.setMedia(newFlat.getMedia());
                    flat.setSecurity(newFlat.getSecurity());
                    return flatRepository.save(flat);
                })
                .orElseGet(() -> {
                    newFlat.setId(Long.parseLong(id));
                    return flatRepository.save(newFlat);
                });
    }

    public void deleteFlat(String id) {
        flatRepository.deleteById(Long.parseLong(id));
    }

    private List<String> getImagesUrls(List<String> images) {
        List<String> urls = new ArrayList<>();
        for(String image : images) {
            byte[] bytes = Base64.getDecoder().decode(image);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date());
            String fileExtension = "";

            try {
                String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
                String delimiter="[/]";
                String[] tokens = mimeType.split(delimiter);
                fileExtension = tokens[1];
            } catch (IOException e) {
                e.printStackTrace();
            }

            ftpFileWriter.open();
            if(ftpFileWriter.isConnected()){
                ftpFileWriter.saveFile(inputStream, "/ffr/images/image_" + currentDate + "." + fileExtension, false);
            }
            ftpFileWriter.close();

            urls.add("/ffr/images/image_" + currentDate + "." + fileExtension);
        }
        return urls;
    }
}