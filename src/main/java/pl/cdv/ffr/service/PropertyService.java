package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.model.PropertyStatus;
import pl.cdv.ffr.repository.PropertyRepository;
import pl.cdv.ffr.utils.ftp.FTPHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService extends BaseService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    FTPHelper ftpHelper;

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
                    copyNonNullProperties(newProperty, property);
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

    private List<String> getImagesUrls(List<String> base64List) {
        List<String> urls = new ArrayList<>();
        if (base64List != null && !base64List.isEmpty()) {
            for (String base64 : base64List) {

                InputStream in;
                String fileUrl;
                Date now = new Date();

                String imageDataBytes = base64.substring(base64.indexOf(",") + 1);
                String typeFileMime = base64.substring(0, base64.indexOf(";"));
                String extension = typeFileMime.substring(typeFileMime.indexOf("/") + 1);

                try {
                    in = ftpHelper.getDecodedInputStream(imageDataBytes, extension);
                    fileUrl = ftpHelper.createAndSaveDecodedFile(in, extension, now);
                    urls.add(fileUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return urls;
    }
}