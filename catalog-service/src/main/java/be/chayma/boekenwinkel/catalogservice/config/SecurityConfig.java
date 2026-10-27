package be.chayma.boekenwinkel.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuratieklasse voor Spring Security.
 */
@Configuration
public class SecurityConfig {

    /**
     * Deze bean zorgt ervoor dat wachtwoorden worden gehasht met BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Deze bean bepaalt welke endpoints beveiligd zijn en welke niet.
     * We zetten de standaard beveiliging uit voor nu, zodat we via Swagger kunnen testen.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Zet CSRF (Cross-Site Request Forgery) beveiliging uit voor API's
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Sta ALLE requests toe (voor nu)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 3. Zet het standaard inlogformulier uit (we maken zelf een login endpoint)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}