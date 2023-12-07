package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractsController {

    @GetMapping("/contracts")
    public String getClientsPage() {
        return "contracts.html";
    }
}