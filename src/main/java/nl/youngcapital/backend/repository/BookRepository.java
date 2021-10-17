package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
