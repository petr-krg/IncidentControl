package krg.petr.naumen.utills;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import krg.petr.naumen.model.*;
import krg.petr.naumen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {

        final long DEPARTMENT_ID = 10L;
        final long POSITION_ID = 13L;

        Optional<User> admin = userRepository.findByName("admin");

        if (admin.isEmpty()) {
                
            Department department = departmentRepository.findById(DEPARTMENT_ID)
                    .orElseThrow(() -> new EntityNotFoundException(format("Department by ID:%d not found!", DEPARTMENT_ID)));

            Position position = positionRepository.findById(POSITION_ID)
                    .orElseThrow(() -> new EntityNotFoundException(format("Position by ID:%d not found!", POSITION_ID)));

            Role roleAdministrator = roleRepository.findByName("ADMINISTRATOR")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ADMINISTRATOR");
                        return roleRepository.save(newRole);
                    });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            Person person = new Person();
            person.setFirstName("Админ");
            person.setLastName("Администраторов");
            person.setPatName("Админович");
            person.setEmail("admin@mail.kz");
            person.setPhone("+777711223333");
            person.setGender(0);
            person.setEmployee(1);
            person.setBirthDate(LocalDate.parse("01.01.2001", formatter));
            person = personRepository.save(person);

            User newAdmin = new User();
            newAdmin.setLogin("admin");
            newAdmin.setName("admin");
            newAdmin.setPassword(passwordEncoder.encode("admin"));
            newAdmin.setPerson(person);
            newAdmin.setDepartment(department);
            newAdmin.setPosition(position);
            newAdmin.addRole(roleAdministrator);

            userRepository.save(newAdmin);
        }
    }
}