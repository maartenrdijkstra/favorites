package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Quote;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    public List<Quote> getQuotesByUserOrderByIdDesc(User user);
}
