package krg.petr.naumen.controller;

import krg.petr.naumen.dto.ClientsListDTO;
import krg.petr.naumen.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}