package com.github.kawis77.workshop.endproject.web.controller;

import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.dao.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/newuser")
    public String prepareAddUser(Model model) {
        model.addAttribute("adduser", new UserEntity());
        return "user/create-user";
    }

    @PostMapping("/adduser")
    public String proccesAddUser(UserEntity user) {
        userRepository.save(user);
        return "user/created";
    }
}
