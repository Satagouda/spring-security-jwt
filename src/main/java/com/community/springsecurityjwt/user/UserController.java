package com.community.springsecurityjwt.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public String profile(){
        return "User profile";
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/moderate")
    public String moderate(){
        return "Moderator panel";
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Admin panel";
    }
}
