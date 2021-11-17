package com.github.kawis77.workshop.endproject.service;


import com.github.kawis77.workshop.endproject.dao.entity.UserEntity;
import com.github.kawis77.workshop.endproject.dao.repository.UserRepository;
import com.github.kawis77.workshop.endproject.service.mapper.UserMapper;
import com.github.kawis77.workshop.endproject.web.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMenagerService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMenagerService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserModel user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole("ROLE_USER");
        UserEntity savedUser = userMapper.fromModel(user);
        userRepository.save(savedUser);
    }
}
