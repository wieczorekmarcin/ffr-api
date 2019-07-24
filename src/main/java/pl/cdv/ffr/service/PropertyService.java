package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.repository.BillRepository;
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
    BillRepository billRepository;

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

            Bill bill = createEmptyBill();
            List<Bill> bills = new ArrayList<>();
            bills.add(bill);
            property.setBills(bills);

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

    private Bill createEmptyBill() {
        Electricity electricity = new Electricity();
        electricity.setAmount("0");
        electricity.setStatus("0");
        electricity.setUsed("0");
        electricity.setRate("0.62");
        electricity.setVisible(true);
        electricity.setDate(getCurrentDate());

        Heating heating = new Heating();
        heating.setAmount("0");
        heating.setStatus("0");
        heating.setUsed("0");
        heating.setRate("80.00");
        heating.setVisible(true);
        heating.setDate(getCurrentDate());

        ColdWater coldWater = new ColdWater();
        coldWater.setAmount("0");
        coldWater.setStatus("0");
        coldWater.setUsed("0");
        coldWater.setRate("14.36");
        coldWater.setVisible(true);
        coldWater.setDate(getCurrentDate());

        HotWater hotWater = new HotWater();
        hotWater.setAmount("0");
        hotWater.setStatus("0");
        hotWater.setUsed("0");
        hotWater.setRate("28.36");
        hotWater.setVisible(true);
        hotWater.setDate(getCurrentDate());

        CommonPart commonPart = new CommonPart();
        commonPart.setAmount("0");
        commonPart.setStatus("0");
        commonPart.setUsed("0");
        commonPart.setRate("1");
        commonPart.setVisible(true);
        commonPart.setDate(getCurrentDate());

        RepairFund repairFund = new RepairFund();
        repairFund.setAmount("0");
        repairFund.setStatus("0");
        repairFund.setUsed("0");
        repairFund.setRate("1");
        repairFund.setVisible(true);
        repairFund.setDate(getCurrentDate());

        Trash trash = new Trash();
        trash.setAmount("0");
        trash.setStatus("0");
        trash.setUsed("0");
        trash.setRate("15.00");
        trash.setVisible(true);
        trash.setDate(getCurrentDate());

        Bill bill = new Bill();
        bill.setColdWater(coldWater);
        bill.setCommonPart(commonPart);
        bill.setElectricity(electricity);
        bill.setHeating(heating);
        bill.setHotWater(hotWater);
        bill.setRepairFund(repairFund);
        bill.setTrash(trash);

        return billRepository.save(bill);
    }
}