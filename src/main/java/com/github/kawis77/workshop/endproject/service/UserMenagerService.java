package com.github.kawis77.workshop.endproject.service;


import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.dao.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMenagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMenagerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserEntity user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
