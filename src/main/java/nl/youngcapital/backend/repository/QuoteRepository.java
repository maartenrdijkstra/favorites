package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
