package com.community.springsecurityjwt.user;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@AllArgsConstructor
public class AuthFailureListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final OurUserDetailedService userService;

    private static final int MAX_FAILED_ATTEMPTS = 5;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        String username = event.getAuthentication().getName();

        AppUser user = userService.findUser(username);

        if(user == null) return;

        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);

        if(attempts >= MAX_FAILED_ATTEMPTS){
            user.setAccountNonLocked(false);
        }
    }
}