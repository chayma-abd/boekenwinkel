package be.chayma.boekenwinkel.catalogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Een boek in de catalogus van de boekenwinkel.
 * Het ISBN is de primaire sleutel, want dat is per boek uniek.
 */
@Entity
public class Book {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authors = new ArrayList<>();

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    private int stock;

    private String genre;

    /** Lege constructor, verplicht voor JPA/Hibernate. */
    public Book() {
    }

    public Book(String isbn, String title, List<String> authors, String description,
                BigDecimal price, int stock, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.genre = genre;
    }
}