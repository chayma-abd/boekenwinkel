package be.chayma.boekenwinkel.catalogservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object voor een boek. Dit is wat de frontend ziet en doorstuurt,
 * los van de entity {@code Book} die in de databank zit.
 *
 * @param isbn        unieke code van het boek
 * @param title       titel van het boek
 * @param authors     minstens één auteur
 * @param description korte omschrijving (maximum 2000 tekens)
 * @param price       prijs in euro, groter dan 0
 * @param stock       aantal exemplaren in de winkel, nooit negatief
 * @param genre       genre van het boek
 */
public record BookDto(
        @NotBlank String isbn,
        @NotBlank String title,
        @NotEmpty List<String> authors,
        @Size(max = 2000) String description,
        @NotNull @Positive BigDecimal price,
        @Min(0) int stock,
        String genre) {
}