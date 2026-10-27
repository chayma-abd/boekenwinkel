package be.chayma.boekenwinkel.catalogservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO voor het maken van een reservering.
 */
public record ReservationRequest(
        @NotNull(message = "User ID mag niet leeg zijn")
        Long userId,

        @NotBlank(message = "ISBN mag niet leeg zijn")
        String isbn,

        @Min(value = 1, message = "Je moet minstens 1 boek reserveren")
        int quantity
) {}