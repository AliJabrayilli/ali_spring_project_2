package com.ali_spring_project_2.com.ali_spring_project_2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "users") // Указывает имя таблицы
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное имя пользователя
    private String username;

    @Column(nullable = false) // Пароль не может быть пустым
    private String password;

    @Column(nullable = false) // Поле для включения/выключения пользователя
    private boolean enabled = true; // По умолчанию пользователь активен

    @OneToMany(mappedBy = "user")
    private List<Product> products; // Связь с продуктами

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}