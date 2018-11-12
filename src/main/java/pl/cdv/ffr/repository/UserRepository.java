package pl.cdv.ffr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cdv.ffr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
