package krg.petr.naumen.controller;

import krg.petr.naumen.dto.ClientContactDTO;
import krg.petr.naumen.dto.ClientsListDTO;
import krg.petr.naumen.dto.UserProfileDTO;
import krg.petr.naumen.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ClientsController {

    @Autowired
    public ClientsService clientsService;

    @GetMapping("/clients")
    public String showClients(Model model) {
        List<ClientsListDTO> clientsList = clientsService.getClientsList();
        model.addAttribute("clientsList", clientsList);

        return "clients";
    }

    @GetMapping("/clients/сontact-persons")
    public String showContactPersons(Model model) {
        List<ClientsListDTO> clientsList = clientsService.getClientsList();
        model.addAttribute("clientsList", clientsList);

        return "clients";
    }


    @PostMapping("/clients/add-client-contact")
    public String addClientContact(@ModelAttribute ClientContactDTO clientContactDTO,
                                                   RedirectAttributes redirectAttributes) {
        try {
            clientsService.addContact(clientContactDTO);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "clients";
    }

}