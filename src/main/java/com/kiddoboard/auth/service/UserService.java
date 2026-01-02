package com.kiddoboard.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiddoboard.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createAdminUser(String adminEmail, String adminPassword) {
        userRepository.findByEmail(adminEmail).orElseGet(() -> {
            var adminUser = com.kiddoboard.auth.entity.User.builder()
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .roles(java.util.Set.of("ADMIN"))
                .build();
            return userRepository.save(adminUser);
        });
    }

}
