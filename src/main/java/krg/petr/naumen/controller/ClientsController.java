package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientsController {

    @GetMapping("/clients")
    public String getClientsPage() {
        return "clients.html";
    }
}