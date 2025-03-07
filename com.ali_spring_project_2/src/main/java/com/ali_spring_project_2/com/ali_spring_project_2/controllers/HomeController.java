package com.ali_spring_project_2.com.ali_spring_project_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String home(Model model) {
        model.addAttribute("message", "Добро пожаловать на главную страницу!"); // Добавление сообщения в модель
        return "home";
    }
}
