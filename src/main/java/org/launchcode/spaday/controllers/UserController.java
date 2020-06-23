package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(@ModelAttribute @Valid User user, Errors errors, Model model, String verify) {

        if (errors.hasErrors()) {
            model.addAttribute("error", errors);
            return "user/add";
        }

        if (user.getPassword().equals(verify)) {
            model.addAttribute("user", user);
            model.addAttribute("verify", verify);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
        }
            return "user/index";
    }
}
