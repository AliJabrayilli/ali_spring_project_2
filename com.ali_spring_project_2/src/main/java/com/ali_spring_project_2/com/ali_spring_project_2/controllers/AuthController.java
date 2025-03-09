package com.ali_spring_project_2.com.ali_spring_project_2.controllers;

import com.ali_spring_project_2.com.ali_spring_project_2.models.LoginForm;
import com.ali_spring_project_2.com.ali_spring_project_2.models.User;
import com.ali_spring_project_2.com.ali_spring_project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
public class AuthController {

    @Autowired
    private UserService userService; // Сервис для управления пользователями

    @Autowired
    private AuthenticationManager authenticationManager; // Менеджер аутентификации

    @GetMapping("/login") // Обрабатывает GET-запросы по адресу "/login"
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // Возвращаем имя представления (например, login.html)
    }

    @GetMapping("/register") // Обрабатывает GET-запросы по адресу "/register"
    public String register(Model model) {
        model.addAttribute("user", new User()); // Передаем новый объект User в модель для формы
        return "register"; // Возвращаем имя представления (например, register.html)
    }

    @PostMapping("/login-process") // Обрабатывает POST-запросы по адресу "/login"
    public String loginUser(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model) {

        // Аутентификация пользователя
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword()));
        return "redirect:/home"; // Перенаправляем на главную страницу после успешной аутентификации
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        if (userService.registerUser(user)) {
            return "redirect:/login"; // Перенаправляем на страницу входа
        } else {
            model.addAttribute("error", "Пользователь с таким именем уже существует."); // Сообщение об ошибке
            return "register"; // Возвращаем на страницу регистрации
        }
    }
}
