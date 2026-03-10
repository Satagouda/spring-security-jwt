package com.community.springsecurityjwt.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OurUserDetailedService implements UserDetailsService {

    private final Map<String, AppUser> users = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) {

        AppUser user = users.get(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found");

        return new SecurityUser(user);
    }
    public AppUser findUser(String username){
        return users.get(username);
    }

    public void addUser(AppUser user){
        users.put(user.getUsername(), user);
    }
}
