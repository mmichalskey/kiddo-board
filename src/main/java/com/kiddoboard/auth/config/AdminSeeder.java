package com.kiddoboard.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.kiddoboard.auth.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminSeeder {

    private final UserService userService;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @PostConstruct
    public void seedAdminUser() {
        if (!verifyEnvironmentVariables()) {
            throw new IllegalStateException("Admin seeding failed: ADMIN_EMAIL or ADMIN_PASSWORD environment variables are not set.");
        }

        userService.createAdminUser(adminEmail, adminPassword);
    }

    private boolean verifyEnvironmentVariables() {
        return adminEmail != null && !adminEmail.isEmpty()
            && adminPassword != null && !adminPassword.isEmpty();
    }

}
