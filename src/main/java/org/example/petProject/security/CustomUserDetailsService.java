package org.example.petProject.security;

import org.example.petProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс реализует интерфейс {@link org.springframework.security.core.userdetails.UserDetailsService}
 * для загрузки пользовательских данных
 * с внедренной зависимостью интерфейса {@link org.example.petProject.service.UserService}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод находит пользователя по имени пользователя.
     *
     * @param username имя искомого пользователя
     * @return полностью заполненная запись пользователя (никогда не равна null)
     * @throws UsernameNotFoundException если пользователя не удалось найти или у пользователя нет GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            org.example.petProject.model.User user = userService.getUserByLogin(username);
            return new User(user.getLogin(), user.getPassword(), List.of(
                    new SimpleGrantedAuthority(user.getRole())));
        } catch (Exception e) {
            throw new UsernameNotFoundException(username);
        }

    }
}
