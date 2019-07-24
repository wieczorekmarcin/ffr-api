package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Alert;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.repository.AlertRepository;
import pl.cdv.ffr.repository.PropertyRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AlertService extends BaseService {

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<Alert> findAllPropertyAlerts(HttpServletRequest request, String property_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Alert> alerts = new ArrayList<>();
        if (isRentier(user)) {
            user.getRentier().getProperties().forEach(p -> {
                if (p.getId() == Long.parseLong(property_ID)) {
                    p.getAlerts().forEach(a -> {
                        if (a.isVisible()) {
                            alerts.add(a);
                        }
                    });
                }
            });
        } else {
            Property tenatProperty = user.getTenat().getProperty();
            if (tenatProperty != null && tenatProperty.getId() == Long.parseLong(property_ID)) {
                tenatProperty.getAlerts().forEach(a -> {
                    if (a.isVisible()) {
                        alerts.add(a);
                    }
                });
            }
        }
        return alerts;
    }

    public Alert findPropertyAlertById(HttpServletRequest request, String property_ID, String alert_id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            for (Property property : user.getRentier().getProperties()) {
                if (property.getId() == Long.parseLong(property_ID)) {
                    for (Alert alert : property.getAlerts()) {
                        if (alert.getId() == Long.parseLong(alert_id) && alert.isVisible()) {
                            return alert;
                        }
                    }
                }
            }
        } else {
            if (user.getTenat().getProperty().getId() == Long.parseLong(property_ID)) {
                for (Alert alert : user.getTenat().getProperty().getAlerts()) {
                    if (alert.getId() == Long.parseLong(alert_id) && alert.isVisible()) {
                        return alert;
                    }
                }
            }
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " has no property with id:" + property_ID + " or bill with id:" + alert_id);
    }

    public Alert createPropertyAlert(HttpServletRequest request, String property_ID, Alert alert) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        alert.setCreatedDate(getCurrentDate());
        alertRepository.save(alert);

        Property property;
        if (isRentier(user)) {
            property = user.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();
        } else {
            property = Stream.of(user.getTenat().getProperty())
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();
        }

        List<Alert> alerts = property.getAlerts();
        alerts.add(alert);
        property.setAlerts(alerts);

        propertyRepository.save(property);

        return alert;
    }

    public Alert updatePropertyAlert(HttpServletRequest request, String property_ID, String alert_id, Alert newAlert) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        Property property;
        if (isRentier(user)) {
            property = user.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();
        } else {
            property = Stream.of(user.getTenat().getProperty())
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();
        }

        return  property.getAlerts().stream()
                .filter(a -> a.getId() == Long.parseLong(alert_id))
                .findFirst()
                .map(alert -> {
                    copyNonNullProperties(newAlert, alert);
                    return alertRepository.save(alert);
                })
                .orElseGet(() -> {
                    newAlert.setId(Long.parseLong(alert_id));
                    return alertRepository.save(newAlert);
                });
    }

    public void deletePropertyAlert(HttpServletRequest request, String property_ID, String alert_id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        Alert alert = null;
        for (Property property : user.getRentier().getProperties()) {
            if (property.getId() == Long.parseLong(property_ID)) {
                for (Alert a : property.getAlerts()) {
                    if (a.getId() == Long.parseLong(alert_id)) {
                        alert = a;
                    }
                }
            }
        }
        if (alert != null) {
            alertRepository.delete(alert);
        }
    }
}
