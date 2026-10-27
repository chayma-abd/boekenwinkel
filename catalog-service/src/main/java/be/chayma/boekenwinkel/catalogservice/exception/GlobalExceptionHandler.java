package be.chayma.boekenwinkel.catalogservice.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

/**
 * Zet exceptions uit de service om naar duidelijke HTTP-antwoorden
 * volgens de standaard voor foutmeldingen (RFC 7807).
 */
@Hidden // Zorgt ervoor dat Swagger deze klasse negeert
@RestControllerAdvice(basePackages = "be.chayma.boekenwinkel.catalogservice.controller") // Beperkt tot jouw controllers
public class GlobalExceptionHandler {

    /**
     * Een boek werd niet gevonden: antwoordt met 404 Not Found.
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ProblemDetail handleBookNotFound(BookNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("https://boekenwinkel.example/problems/book-not-found"));
        problem.setTitle("Boek niet gevonden");
        return problem;
    }

    /**
     * Het ISBN bestaat al: antwoordt met 409 Conflict.
     */
    @ExceptionHandler(BookAlreadyExistsException.class)
    public ProblemDetail handleBookAlreadyExists(BookAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setType(URI.create("https://boekenwinkel.example/problems/book-already-exists"));
        problem.setTitle("Boek bestaat al");
        return problem;
    }

    /**
     * De gebruiker bestaat al: antwoordt met 409 Conflict.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setType(URI.create("https://boekenwinkel.example/problems/user-already-exists"));
        problem.setTitle("Gebruiker bestaat al");
        return problem;
    }
}