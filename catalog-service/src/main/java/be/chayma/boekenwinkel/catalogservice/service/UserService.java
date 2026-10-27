package be.chayma.boekenwinkel.catalogservice.service;

import be.chayma.boekenwinkel.catalogservice.dto.RegisterRequest;
import be.chayma.boekenwinkel.catalogservice.dto.UserResponse;
import be.chayma.boekenwinkel.catalogservice.exception.UserAlreadyExistsException;
import be.chayma.boekenwinkel.catalogservice.model.User;
import be.chayma.boekenwinkel.catalogservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection (Dependency Injection)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registreert een nieuwe gebruiker.
     * @param request De data die de gebruiker heeft ingestuurd.
     * @return De aangemaakte gebruiker (zonder wachtwoord).
     */
    public UserResponse register(RegisterRequest request) {

        // 1. Check of de gebruiker al bestaat (edge case!)
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("Er bestaat al een gebruiker met dit e-mailadres.");
        }

        // 2. Maak een nieuwe User aan
        User user = new User();
        user.setEmail(request.email());
        user.setName(request.name());

        // 3. Hash het wachtwoord! (NOOIT platte tekst opslaan)
        String hashedPassword = passwordEncoder.encode(request.password());
        user.setPasswordHash(hashedPassword);

        // 4. Sla de gebruiker op in de database
        User savedUser = userRepository.save(user);

        // 5. Stuur een UserResponse terug (zonder wachtwoord)
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    }
}