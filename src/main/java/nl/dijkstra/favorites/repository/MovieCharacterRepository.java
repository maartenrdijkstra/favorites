package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
}
