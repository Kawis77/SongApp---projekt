package com.github.kawis77.workshop.endproject.service;

import com.github.kawis77.workshop.endproject.model.User;
import com.github.kawis77.workshop.endproject.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomDetailUserSerivce implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomDetailUserSerivce(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(username);
        optionalUser
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole()))
                        )
                ).orElseThrow(()-> new UsernameNotFoundException("Username" + username + "Not found")

                );
        return null;
    }
}
