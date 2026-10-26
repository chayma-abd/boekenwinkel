package be.chayma.boekenwinkel.catalogservice.controller;

import be.chayma.boekenwinkel.catalogservice.dto.BookDto;
import be.chayma.boekenwinkel.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * REST-controller voor de catalogus van boeken.
 * Neemt HTTP-aanvragen aan en laat het eigenlijke werk over aan {@link BookService}.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Geeft alle boeken uit de catalogus terug.
     */
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Geeft het boek met het opgegeven ISBN terug.
     */
    @GetMapping("/{isbn}")
    public BookDto getBook(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }

    /**
     * Voegt een nieuw boek toe en antwoordt met 201 Created.
     */
    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) {
        BookDto created = bookService.createBook(dto);
        return ResponseEntity
                .created(URI.create("/api/books/" + created.isbn()))
                .body(created);
    }
}