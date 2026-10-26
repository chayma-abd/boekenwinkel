package be.chayma.boekenwinkel.catalogservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO voor het registreren van een nieuwe gebruiker.
 * Bevat het platte wachtwoord dat de gebruiker intypt.
 */
public record RegisterRequest(
        @NotBlank(message = "E-mailadres mag niet leeg zijn")
        @Email(message = "Ongeldig e-mailadres")
        String email,

        @NotBlank(message = "Naam mag niet leeg zijn")
        String name,

        @NotBlank(message = "Wachtwoord mag niet leeg zijn")
        @Size(min = 6, message = "Wachtwoord moet minstens 6 tekens lang zijn")
        String password
) {}