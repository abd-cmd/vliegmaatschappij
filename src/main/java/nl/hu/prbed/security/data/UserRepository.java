package nl.hu.prbed.security.data;


import nl.hu.prbed.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This is a magic interface, which is automatically implemented
 * by Spring based on the chosen data storage configuration
 * when the application is started.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}