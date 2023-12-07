package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {

    @GetMapping("/equipment")
    public String getClientsPage() {
        return "equipment.html";
    }
}