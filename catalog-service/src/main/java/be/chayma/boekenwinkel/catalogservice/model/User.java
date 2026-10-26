package be.chayma.boekenwinkel.catalogservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "app_user") // "user" is een gereserveerd woord in SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email(message = "Ongeldig e-mailadres")
    @NotBlank(message = "E-mailadres mag niet leeg zijn")
    private String email;

    @NotBlank(message = "Naam mag niet leeg zijn")
    private String name;

    // We slaan NOOIT platte wachtwoorden op. Dit is de hash.
    @NotBlank
    private String passwordHash;

    // Lege constructor is VERPLICHT voor Hibernate
    public User() {
    }

    // Getters en Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}