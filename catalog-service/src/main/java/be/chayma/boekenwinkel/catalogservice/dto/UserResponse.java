package be.chayma.boekenwinkel.catalogservice.dto;

/**
 * DTO voor het terugsturen van gebruikersinformatie naar de frontend.
 * Bevat GEEN wachtwoord (veiligheid!).
 */
public record UserResponse(
        Long id,
        String email,
        String name
) {}
