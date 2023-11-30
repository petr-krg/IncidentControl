package krg.petr.naumen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticateController {

    @GetMapping({"/", "/login"})
    public String showWelcomePage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }

        return "/welcome";
    }
}