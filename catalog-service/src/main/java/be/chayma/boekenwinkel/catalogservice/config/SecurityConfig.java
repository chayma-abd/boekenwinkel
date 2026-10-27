package be.chayma.boekenwinkel.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuratieklasse voor Spring Security.
 * We definiëren hier de PasswordEncoder bean zodat we wachtwoorden veilig kunnen hashen.
 */
@Configuration
public class SecurityConfig {

    /**
     * Deze bean zorgt ervoor dat wachtwoorden worden gehasht met het BCrypt-algoritme.
     * BCrypt is de industriestandaard voor wachtwoordopslag.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}