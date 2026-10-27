package be.chayma.boekenwinkel.catalogservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO voor het inloggen van een gebruiker.
 */
public record LoginRequest(
        @NotBlank(message = "E-mailadres mag niet leeg zijn")
        @Email(message = "Ongeldig e-mailadres")
        String email,

        @NotBlank(message = "Wachtwoord mag niet leeg zijn")
        String password
) {}