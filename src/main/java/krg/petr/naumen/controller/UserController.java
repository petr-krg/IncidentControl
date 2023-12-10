package krg.petr.naumen.controller;

import krg.petr.naumen.dto.UserPasswordChangeDTO;
import krg.petr.naumen.dto.UserProfileDTO;
import krg.petr.naumen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/profile-info")
    public ResponseEntity<UserProfileDTO> getUserProfile(Principal principal) {
        UserProfileDTO userProfileDTO = userService.getUserProfile(principal.getName());

        return  ResponseEntity.ok(userProfileDTO);
    }

    @PostMapping("/profile-update")
    public String updateUserProfile(@ModelAttribute UserProfileDTO userProfileDTO, Principal principal,
                                    RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserProfile(principal.getName(), userProfileDTO);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "index";
    }

    @PostMapping("/change-password")
    public String changeUserPassword(@ModelAttribute UserPasswordChangeDTO passwordChangeDTO, Principal principal,
                                     RedirectAttributes redirectAttributes) {
        try {
            userService.changePassword(principal.getName(), passwordChangeDTO.getCurrentPassword(),
                    passwordChangeDTO.getNewPassword());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "index.html";
    }
}