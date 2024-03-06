package org.example.petProject.repository;

import org.example.petProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс, наследующий базовый интерфейс репозитория Spring Data JPA для
 * обеспечения операций над объектами класса {@link org.example.petProject.model.User}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Метод позволяет найти объект - пользователь приложения по его имени
     *
     * @param login имя пользователя приложения
     * @return объект - искомый пользователь
     */
    User findByLogin(String login);
}
