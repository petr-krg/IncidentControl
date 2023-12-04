package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "index", "index.html"})
    public String getHomePage() {
        return "index.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "welcome.html";
    }
}