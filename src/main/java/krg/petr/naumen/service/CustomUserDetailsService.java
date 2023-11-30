package krg.petr.naumen.service;

import krg.petr.naumen.model.CustomUserDetail;
import krg.petr.naumen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import krg.petr.naumen.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(name);

        System.out.println("Проверяемый пароль: " + user.getPassword());

        if (user != null) {
            return new CustomUserDetail(user);
        } else {
            throw new UsernameNotFoundException(format("User with login %s could not be found", name));
        }
    }
}