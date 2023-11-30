package krg.petr.naumen.repository;

import krg.petr.naumen.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long userID);
    Page<User> getUsers(Pageable pageable);
    Optional<User> getUserByLogin(String userLogin);
}