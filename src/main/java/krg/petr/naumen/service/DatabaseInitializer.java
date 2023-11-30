package krg.petr.naumen.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import krg.petr.naumen.model.Role;
import krg.petr.naumen.model.User;
import krg.petr.naumen.repository.RoleRepository;
import krg.petr.naumen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PostConstruct
    public void init() {

        try {
            Role adminRole = roleRepository.findByName("ADMINISTRATOR")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ADMINISTRATOR");
                        return roleRepository.save(newRole);
                    });

            Optional<User> admin = userRepository.findByName("admin");

            if (admin.isEmpty()) {
                User newAdmin = new User();
                newAdmin.setLogin("admin");
                newAdmin.setName("admin");
                newAdmin.setPassword(passwordEncoder.encode("admin"));
                newAdmin.setEmail("admin@mail.kz");
                newAdmin.setPhone("+777711223333");
                newAdmin.setRoles(Set.of(adminRole));

                adminRole.getUsers().add(newAdmin);
                userRepository.save(newAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}