package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Country;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public List<Country> getCountriesByUserOrderByIdDesc(User user);
}
