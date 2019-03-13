package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.model.User;
import pl.cdv.ffr.model.UserType;
import pl.cdv.ffr.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

    public List<JwtUser> findAllUsers() {
        return userRepository.findAll().stream().map(user -> JwtUserFactory.create(user)).collect(Collectors.toList());
    }

    public JwtUser findUserById(String id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(Long.parseLong(id)))
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList()).get(0);
    }

    public List<JwtUser> findAllRentiers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.rentier)
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList());
    }

    public JwtUser findRentierById(String id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.rentier)
                .filter(user -> user.getId().equals(Long.parseLong(id)))
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList()).get(0);
    }

    public List<JwtUser> findAllTenats() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.tenat)
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList());
    }

    public JwtUser findTenatById(String id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType() == UserType.tenat)
                .filter(user -> user.getId().equals(Long.parseLong(id)))
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList()).get(0);
    }
}
