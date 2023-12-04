package krg.petr.naumen.service;

import jakarta.transaction.Transactional;
import krg.petr.naumen.dto.UserProfileDTO;
import krg.petr.naumen.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    void changePassword(String userName, String currentPassword, String newPassword) throws Exception;

    UserProfileDTO getUserProfile(String userName);

    @Transactional
    void updateUserProfile(String userName, UserProfileDTO userProfileDTO);
}