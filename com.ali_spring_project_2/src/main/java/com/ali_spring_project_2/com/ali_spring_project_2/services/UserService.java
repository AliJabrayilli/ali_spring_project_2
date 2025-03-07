package com.ali_spring_project_2.com.ali_spring_project_2.services;

import com.ali_spring_project_2.com.ali_spring_project_2.models.Authority;
import com.ali_spring_project_2.com.ali_spring_project_2.repositories.AuthorityRepository;
import com.ali_spring_project_2.com.ali_spring_project_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для работы с пользователями

    @Autowired
    private AuthorityRepository authorityRepository; // Репозиторий для работы с пользователями

    @Autowired
    private PasswordEncoder passwordEncoder; // Кодировщик паролей

    public boolean registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false; // Пользователь уже существует
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем пароль
        userRepository.save(user); // Сохраняем пользователя в базе данных

        // Добавление роли (например, USER) для нового пользователя
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authorityRepository.save(authority); // Сохраните роль в базе данных

        // Связка пользователя с ролью
        userRepository.save(user); // Обновление пользователя с ролью

        return true; // Успешная регистрация
    }
}