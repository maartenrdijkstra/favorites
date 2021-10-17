package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
}
