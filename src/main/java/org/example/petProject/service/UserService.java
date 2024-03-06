package org.example.petProject.service;

import org.example.petProject.model.User;

/**
 * Интерфейс служит для выполнения операций над объектами
 * репозитория {@link org.example.petProject.repository.UserRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
public interface UserService {
    /**
     * Метод позволяет получить объект - пользователь приложения репозитория по его имени
     *
     * @param login имя пользователя приложения
     * @return объект - искомый пользователь
     */
    User getUserByLogin(String login);

}
