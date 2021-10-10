package com.github.kawis77.workshop.endproject.controller;

import com.github.kawis77.workshop.endproject.model.Song;
import com.github.kawis77.workshop.endproject.model.User;
import com.github.kawis77.workshop.endproject.repository.UserRepository;
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
    public String addUser1(Model model) {
        model.addAttribute("adduser", new User());
        return "user/create-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user) {
        userRepository.save(user);
        return "user/created";
    }
}
