package com.github.kawis77.workshop.endproject.web.controller;


import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.service.UserMenagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserMenagerService userMenagerService;

    public RegistrationController(UserMenagerService userMenagerService) {
        this.userMenagerService = userMenagerService;
    }

    @GetMapping("/register")
    public String prepareRegistration(Model model){
        model.addAttribute("user", new UserEntity());
        return "registration";

    }
    @PostMapping("/register")
    public String proccesRegistriation(UserEntity user){
        userMenagerService.registerUser(user);
        return "redirect:/login";
    }
}
