package com.ali_spring_project_2.com.ali_spring_project_2.services;

import com.ali_spring_project_2.com.ali_spring_project_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ali_spring_project_2.com.ali_spring_project_2.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для работы с пользователями

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Верните кастомный объект, который реализует UserDetails
        return new User(user.getId(), user.getUsername(), user.getPassword());
    }
}