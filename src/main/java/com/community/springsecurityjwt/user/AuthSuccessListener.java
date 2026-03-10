package com.community.springsecurityjwt.user;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthSuccessListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    private final OurUserDetailedService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        String username = event.getAuthentication().getName();

        AppUser user = userService.findUser(username);

        if(user != null){
            user.setFailedAttempts(0);
        }
    }
}
