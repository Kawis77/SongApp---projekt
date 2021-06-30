package com.github.kawis77.workshop.endproject.controller;


import com.github.kawis77.workshop.endproject.model.User;
import com.github.kawis77.workshop.endproject.service.UserMenagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    private final UserMenagerService userMenagerService;

    public RegistrationController(UserMenagerService userMenagerService) {
        this.userMenagerService = userMenagerService;
    }

    @GetMapping("/register")
    public String prepareRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";

    }
    @PostMapping("/register")
    public String proccesRegistriation(User user){
        userMenagerService.regiserUser(user);
        return "redirect:/login";
    }
}
