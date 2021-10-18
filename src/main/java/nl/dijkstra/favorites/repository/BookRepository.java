package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
