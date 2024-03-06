package org.example.petProject.service;

import jakarta.annotation.PostConstruct;
import org.example.petProject.model.User;
import org.example.petProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс реализует интерфейс {@link org.example.petProject.service.UserService}
 * с внедренной зависимостью интерфейса {@link org.example.petProject.repository.UserRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Генерация данных тестирования в таблице "customers" базы данных после внедрения зависимостей
    @PostConstruct
    private void generateUsersData() {
        userRepository.save(new User("login1", "pass1", "admin"));
        userRepository.save(new User("login2", "pass2", "user"));
    }


    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
