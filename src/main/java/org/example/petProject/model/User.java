package org.example.petProject.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс служит для хранения в базе данных объектов - пользователей приложения
 * со свойствами <b>id</b>, <b>login</b>, <b>password</b> и <b>role</b>
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Entity
@Data
@Table(name = "customers")
public class User {
    /**
     * Поле номер в таблице базы данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле имя пользователя приложения
     */
    @Column(name = "login")
    private String login;

    /**
     * Поле пароль пользователя приложения
     */
    @Column(name = "password")
    private String password;

    /**
     * Поле группа прав доступа пользователя приложения
     */
    @Column(name = "role")
    private String role;

    /**
     * Конструктор - создание нового объекта класса
     *
     * @see User#User(String, String, String)
     */
    public User() {
    }

    /**
     * Конструктор - создание нового объекта класса
     *
     * @see User#User()
     */
    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
