package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ObjectsController {

    @GetMapping("/objects")
    public String getClientsPage() {
        return "objects.html";
    }
}