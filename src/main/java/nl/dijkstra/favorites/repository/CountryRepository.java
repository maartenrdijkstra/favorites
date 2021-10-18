package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
