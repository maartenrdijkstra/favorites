package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Book;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> getBooksByUserOrderByIdDesc(User user);
}
