package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.repository.AuthorityRepository;
import pl.cdv.ffr.repository.PropertyRepository;
import pl.cdv.ffr.repository.TenatRepository;
import pl.cdv.ffr.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenatService extends BaseService {

    @Autowired
    TenatRepository tenatRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Tenat> findAllTenats() {
        return (List<Tenat>) filterByVisible(tenatRepository.findAll());
    }

    public Tenat findTenatById(String id) {
        Optional<Tenat> byId = tenatRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Tenat createTenat(Tenat tenat) {
        User user = new User();
        user.setFirstname(tenat.getFirstName());
        user.setLastname(tenat.getLastName());
        user.setEmail(tenat.getEmail());
        user.setEnabled(true);
        user.setUsername(user.getEmail());
        user.setLastPasswordResetDate(new Date());

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findById(Long.parseLong("1")).get());
        user.setAuthorities(authorities);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(tenat.getPesel());
        user.setPassword(hashedPassword);

        user.setUserType(UserType.TENAT);
        userRepository.save(user);

        Tenat toReturn = tenatRepository.save(tenat);

        changePropertyStatus(toReturn);

        return toReturn;
    }

    public Tenat updateTenat(Tenat newTenat, String id) {
        return tenatRepository.findById(Long.parseLong(id))
                .map(tenat -> {
                    copyNonNullProperties(newTenat, tenat);
                    //changePropertyStatus(newTenat);
                    return tenatRepository.save(tenat);
                })
                .orElseGet(() -> {
                    newTenat.setId(Long.parseLong(id));
                    return tenatRepository.save(newTenat);
                });
    }


    public void deleteTenat(String id) {
        tenatRepository.deleteById(Long.parseLong(id));
    }

    private void changePropertyStatus(Tenat tenat) {
        Property property = tenat.getProperty();
        if (property == null) {
            return;
        } else {
            property.setPropertyStatus(PropertyStatus.RENTED);
            propertyRepository.save(property);
        }
    }
}
