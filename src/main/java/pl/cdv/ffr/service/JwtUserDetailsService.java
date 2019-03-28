package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
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

    public List<JwtUser> findUserByUserType(UserType userType) {
        return userRepository.findByUserType(userType).stream().map(user -> JwtUserFactory.create(user)).collect(Collectors.toList());
    }

    public JwtUser findUserById(String id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(Long.parseLong(id)))
                .map(user -> JwtUserFactory.create(user)).collect(Collectors.toList()).get(0);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User newUser, String id) {
        return userRepository.findById(Long.parseLong(id))
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    user.setFirstname(newUser.getFirstname());
                    user.setLastname(newUser.getLastname());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(Long.parseLong(id));
                    return userRepository.save(newUser);
                });
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }
}
