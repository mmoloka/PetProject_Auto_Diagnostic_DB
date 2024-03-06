package org.example.petProject.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Класс реализует интерфейс {@link org.springframework.security.crypto.password.PasswordEncoder}
 * для шифрования паролей.
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * Метод шифрует необработанный пароль
     *
     * @param rawPassword необработанный пароль
     * @return зашифрованный пароль
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    /**
     * Метод позволяет убедиться, что зашифрованный пароль, полученный из хранилища, соответствует отправленному
     * необработанному паролю после того, как он также будет зашифрован.
     *
     * @param rawPassword     необработанный пароль
     * @param encodedPassword зашифрованный пароль, полученный из хранилища
     * @return true, если пароли совпадают, и false, если нет
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
