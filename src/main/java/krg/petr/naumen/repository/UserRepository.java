package krg.petr.naumen.repository;

import krg.petr.naumen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    Optional<User> findByName(String name);
}