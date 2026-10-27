package be.chayma.boekenwinkel.catalogservice.controller;

import be.chayma.boekenwinkel.catalogservice.dto.LoginRequest;
import be.chayma.boekenwinkel.catalogservice.dto.RegisterRequest;
import be.chayma.boekenwinkel.catalogservice.dto.UserResponse;
import be.chayma.boekenwinkel.catalogservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * REST-controller voor gebruikersbeheer.
 * Behandelt registratie en login van gebruikers.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor injectie
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registreert een nieuwe gebruiker in het systeem.
     * Geeft een 201 Created terug bij succes.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        UserResponse createdUser = userService.register(request);

        // We geven een 201 Created status terug, met de locatie van de nieuwe user
        return ResponseEntity
                .created(URI.create("/api/users/" + createdUser.id()))
                .body(createdUser);
    }

    /**
     * Logt een gebruiker in.
     * Geeft een 200 OK terug bij succes.
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        UserResponse user = userService.login(request);
        return ResponseEntity.ok(user);
    }
}