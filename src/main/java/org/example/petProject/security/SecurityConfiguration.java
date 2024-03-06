package org.example.petProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс служит для конфигурации настроек безопасности доступа пользователей к приложению
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Configuration
public class SecurityConfiguration {
    /**
     * Метод определяет цепочку фильтров, которую можно сопоставить с HTTP-запросом, чтобы решить,
     * соответствует ли ей запрос.
     *
     * @param httpSecurity объект HttpSecurity позволяет настроить веб-безопасность для определенных HTTP-запросов
     * @return необходимую цепочку фильтров
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/repair/ui/**").hasAnyAuthority("user")
                        .requestMatchers("/repair/**").hasAnyAuthority("admin")
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
