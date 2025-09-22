package com.pla.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:configs.properties")
public class UserDetailsServiceFromEnv implements UserDetailsService {
    @Autowired
    private Environment environment;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String adminUser = environment.getProperty("ADMIN_USER");
        String adminPass = environment.getProperty("ADMIN_PASSWORD");
        if (adminUser == null || adminPass == null) {
            throw new UsernameNotFoundException("ADMIN_USER/ADMIN_PASSWORD not set");
        }
        if (!adminUser.equals(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return User.withUsername(adminUser)
                .password(passwordEncoder.encode(adminPass))
                .roles("ADMIN")
                .build();
    }
}
