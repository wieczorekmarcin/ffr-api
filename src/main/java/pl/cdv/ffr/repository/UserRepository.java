package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cdv.ffr.model.User;
import pl.cdv.ffr.model.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUserType(UserType userType);
}