package com.community.springsecurityjwt.configuration;

import com.community.springsecurityjwt.user.AppUser;
import com.community.springsecurityjwt.user.OurUserDetailedService;
import com.community.springsecurityjwt.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final OurUserDetailedService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){

        userService.addUser(
                AppUser.builder()
                        .username("user")
                        .password(passwordEncoder.encode("password"))
                        .role(Role.USER)
                        .accountNonLocked(true)
                        .build()
        );

        userService.addUser(
                AppUser.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("password"))
                        .role(Role.SUPER_ADMIN)
                        .accountNonLocked(true)
                        .build()
        );
    }
}