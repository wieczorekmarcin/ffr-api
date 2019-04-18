package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.model.PropertyStatus;
import pl.cdv.ffr.repository.PropertyRepository;
import pl.cdv.ffr.utils.ftp.FTPFileWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    FTPFileWriter ftpFileWriter;

    public List<Property> findAllPropertys() {
        return propertyRepository.findAll();
    }

    public Property findPropertyById(String id) {
        Optional<Property> byId = propertyRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public List<Property> findPropertysByStatus(PropertyStatus propertyStatus) {
        return propertyRepository.findByPropertyStatus(propertyStatus);
    }

    public Property createProperty(Property property) {
        if(property.getImages() != null && !property.getImages().isEmpty()) {
            List<String> urls = getImagesUrls(property.getImages());
            property.setImagesUrls(urls);
        }
        return propertyRepository.save(property);
    }

    public Property updateProperty(Property newProperty, String id) {
        return propertyRepository.findById(Long.parseLong(id))
                .map(property -> {
                    property.setDescription(newProperty.getDescription());
                    property.setPropertyStatus(newProperty.getPropertyStatus());
                    property.setTitle(newProperty.getTitle());
                    property.setImagesUrls(getImagesUrls(newProperty.getImages()));
                    property.setAvailableFrom(newProperty.getAvailableFrom());
                    property.setBail(newProperty.getBail());
                    property.setBuildingMaterial(newProperty.getBuildingMaterial());
                    property.setBuildingType(newProperty.getBuildingType());
                    property.setCity(newProperty.getCity());
                    property.setFloor(newProperty.getFloor());
                    property.setFloorsNumber(newProperty.getFloorsNumber());
                    property.setHeating(newProperty.getHeating());
                    property.setWindows(newProperty.getWindows());
                    property.setPostCode(newProperty.getPostCode());
                    property.setPrice(newProperty.getPrice());
                    property.setRoomsNumber(newProperty.getRoomsNumber());
                    property.setStreet(newProperty.getStreet());
                    property.setSurface(newProperty.getSurface());
                    property.setAdditionalInformation(newProperty.getAdditionalInformation());
                    return propertyRepository.save(property);
                })
                .orElseGet(() -> {
                    newProperty.setId(Long.parseLong(id));
                    return propertyRepository.save(newProperty);
                });
    }

    public void deleteProperty(String id) {
        propertyRepository.deleteById(Long.parseLong(id));
    }

    private List<String> getImagesUrls(List<String> images) {
        List<String> urls = new ArrayList<>();
        for (String image : images) {
            boolean isBase64 = image.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
            if (!isBase64) {
                continue;
            }
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
            if (ftpFileWriter.isConnected()){
                ftpFileWriter.saveFile(inputStream, "/images/image_" + currentDate + "." + fileExtension, false);
            }
            ftpFileWriter.close();

            urls.add("http://wieczorekmarcin.usermd.net/ffr/images/image_" + currentDate + "." + fileExtension);
        }
        return urls;
    }
}