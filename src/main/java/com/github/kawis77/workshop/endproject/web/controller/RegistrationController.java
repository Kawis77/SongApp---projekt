package com.github.kawis77.workshop.endproject.web.controller;


import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.service.UserMenagerService;
import com.github.kawis77.workshop.endproject.service.UserService;
import com.github.kawis77.workshop.endproject.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserMenagerService userMenagerService;

    public RegistrationController(UserService userService, UserMenagerService userMenagerService) {
        this.userService = userService;
        this.userMenagerService = userMenagerService;
    }

    @GetMapping("/register")
    public String prepareRegistration(Model model){
        model.addAttribute("user", new UserEntity());
        return "registration";

    }
    @PostMapping("/register")
    public String proccesRegistriation(UserModel userModel){
        userMenagerService.registerUser(userModel);
        return "redirect:/login";
    }
}
