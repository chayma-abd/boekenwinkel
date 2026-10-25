package be.chayma.boekenwinkel.catalogservice.repository;

import be.chayma.boekenwinkel.catalogservice.model.Book;
import org.springframework.data.repository.ListCrudRepository;

/**
 * DAO voor boeken: de enige plaats waar we de tabel "book" aanspreken.
 * Spring Data maakt de implementatie zelf aan bij het opstarten.
 */
public interface BookRepository extends ListCrudRepository<Book, String> {
}
