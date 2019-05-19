package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.repository.PropertyRepository;
import pl.cdv.ffr.repository.UserRepository;
import pl.cdv.ffr.utils.ftp.FTPHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService extends BaseService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    FTPHelper ftpHelper;

    public List<Property> findAllProperties(HttpServletRequest request) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Property> properties = new ArrayList<>();
        if (isRentier(user)) {
            properties = user.getRentier().getProperties();
        } else {
            properties.add(user.getTenat().getProperty());
        }
        properties = (List<Property>) filterByVisible(properties);
        return properties;
    }

    public Property findPropertyById(HttpServletRequest request, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Property> properties = new ArrayList<>();
        if (isRentier(user)) {
            properties = user.getRentier().getProperties();
        } else {
            properties.add(user.getTenat().getProperty());
        }
        properties = (List<Property>) filterByVisible(properties);
        return properties.stream()
                .filter(property -> property.getId() == Long.parseLong(id))
                .findFirst()
                .get();
    }

    public List<Property> findPropertysByStatus(HttpServletRequest request, PropertyStatus propertyStatus) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Property> properties = new ArrayList<>();
        if (isRentier(user)) {
            properties = user.getRentier().getProperties();
        } else {
            properties.add(user.getTenat().getProperty());
        }
        properties = (List<Property>) filterByVisible(properties);
        return properties.stream()
                .filter(property -> property.getPropertyStatus() == propertyStatus)
                .collect(Collectors.toList());
    }

    public Property createProperty(HttpServletRequest request, Property property) {
        JwtUser u1 = userService.getUserInfo(request, tokenHeader);
        if (isRentier(u1)) {
            if(property.getImages() != null && !property.getImages().isEmpty()) {
                List<String> urls = getImagesUrls(property.getImages());
                property.setImagesUrls(urls);
            }
            propertyRepository.save(property);

            User u2 = userRepository.findById(u1.getId()).get();
            List<Property> properties = u2.getRentier().getProperties();
            properties.add(property);
            userRepository.save(u2);
            return property;
        }
        throw new UsernameNotFoundException("User " + u1.getEmail() + " is a tenat. He can't create property");
    }

    public Property updateProperty(HttpServletRequest request, Property newProperty, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            return user.getRentier().getProperties().stream()
                    .filter(property -> property.getId() == Long.parseLong(id))
                    .findFirst()
                    .map(property -> {
                        copyNonNullProperties(newProperty, property);
                        return propertyRepository.save(property);
                    })
                    .orElseGet(() -> {
                        newProperty.setId(Long.parseLong(id));
                        return propertyRepository.save(newProperty);
                    });
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't update property");
    }

    public void deleteProperty(HttpServletRequest request, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Property property = user.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(id))
                    .findFirst()
                    .get();

            propertyRepository.delete(property);
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't delete property");
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
                String directoryName = "images";

                try {
                    in = ftpHelper.getDecodedInputStream(imageDataBytes, extension);
                    fileUrl = ftpHelper.createAndSaveDecodedFile(in, extension, "image", directoryName);
                    urls.add(fileUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return urls;
    }
}