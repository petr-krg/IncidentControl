package krg.petr.naumen.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import krg.petr.naumen.dto.UserProfileDTO;
import krg.petr.naumen.model.*;
import krg.petr.naumen.repository.*;
import krg.petr.naumen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(String userName, String currentPassword, String newPassword) throws Exception {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new Exception(format("User %s not found!", userName)));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new Exception("Invalid current password!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void updateUserRole(User user, Set<Role> newRoles) {

        clearUserRole(user);

        for (Role role : newRoles) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            user.getUserRoles().add(userRole);
        }

        userRepository.save(user);
    }

    public void clearUserRole(User user) {

        for (Iterator<UserRole> iterator = user.getUserRoles().iterator(); iterator.hasNext();) {

            UserRole userRole = iterator.next();
            iterator.remove();
            userRoleRepository.delete(userRole);
        }
    }

    @Override
    public UserProfileDTO getUserProfile(String userName) {

        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found!", userName)));

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserFirstName(user.getPerson().getFirstName());
        userProfileDTO.setUserLastName(user.getPerson().getLastName());
        userProfileDTO.setUserPatName(user.getPerson().getPatName());
        userProfileDTO.setUserBirthDate(user.getPerson().getBirthDate());
        userProfileDTO.setUserGender(user.getPerson().getGenderDisplayName());
        userProfileDTO.setUserDisplayName(user.getName());
        userProfileDTO.setUserLogin(user.getLogin());
        userProfileDTO.setUserEmail(user.getPerson().getEmail());
        userProfileDTO.setUserPhone(user.getPerson().getPhone());

        Department department = departmentRepository.findById(user.getDepartment().getParent().getId())
                .orElseThrow(() ->  new EntityNotFoundException(
                        format("Department by ID:%d not found!", user.getDepartment().getParent().getId())));
        List<String> allDepartment = departmentRepository.findByParentIsNull()
                .stream()
                .map(Department::getName)
                .toList();
        String currentDepartment = department.getName();

        String currentDivision = user.getDepartment().getName();
        List<String> allDivision = departmentRepository.findByParentId(user.getDepartment().getParent().getId())
                .stream()
                .map(Department::getName)
                .toList();

        String currentPosition = user.getPosition().getName();
        List<String> allPosition = positionRepository.findByDepartmentId(user.getPosition().getDepartment().getId())
                .stream()
                .map(Position::getName)
                .toList();

//        userProfileDTO.setCurrentRole(String.join(", ", currentRoles));
//        Set<String> currentRoles = user.getUserRoles().stream()
//                .map(userRole -> userRole.getRole().getName())
//                .collect(Collectors.toSet());
        String currentRole = user.getUserRoles().iterator().next().getRole().getName();
        List<String> allRoles = roleRepository.findAll().stream()
                .map(Role::getName)
                .toList();

        userProfileDTO.setCurrentRole(currentRole);
        userProfileDTO.setAllRoles(allRoles);
        userProfileDTO.setCurrentDepartment(currentDepartment);
        userProfileDTO.setAllDepartment(allDepartment);
        userProfileDTO.setCurrentDivision(currentDivision);
        userProfileDTO.setAllDivision(allDivision);
        userProfileDTO.setCurrentPosition(currentPosition);
        userProfileDTO.setAllPosition(allPosition);

        return userProfileDTO;
    }

    @Transactional
    @Override
    public void updateUserProfile(String userName, UserProfileDTO userProfileDTO) {
        try {
            User user = userRepository.findByName(userName)
                    .orElseThrow(()-> new UsernameNotFoundException(format("User not found by name = %s!", userName)));

            Person person = user.getPerson();
            if (person != null) {
                person.setFirstName(userProfileDTO.getUserFirstName());
                person.setLastName(userProfileDTO.getUserLastName());
                person.setPatName(userProfileDTO.getUserPatName());
                person.setBirthDate(userProfileDTO.getUserBirthDate());
                person.setEmail(userProfileDTO.getUserEmail());
                person.setPhone(userProfileDTO.getUserPhone());

                personRepository.save(person);
            }

            if (!userProfileDTO.getCurrentRole().isEmpty()) {
                Role role = roleRepository.findByName(userProfileDTO.getCurrentRole())
                        .orElseThrow(() -> new EntityNotFoundException(
                                format("Role [%s] not found", userProfileDTO.getCurrentRole())));

                //user.getUserRoles().clear();
                clearUserRole(user);
                user.addRole(role);
            }

            if (!userProfileDTO.getCurrentDepartment().isEmpty()) {
                Department division = departmentRepository.findByName(userProfileDTO.getCurrentDivision());

                user.setDepartment(division);
            }

            if (!userProfileDTO.getCurrentPosition().isEmpty()) {
                Position position = positionRepository.findByName(userProfileDTO.getCurrentPosition());

                user.setPosition(position);
            }

            user.setName(userProfileDTO.getUserDisplayName());
            user.setLogin(userProfileDTO.getUserLogin());


            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}