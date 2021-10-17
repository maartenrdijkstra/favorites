package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke, Long> {
}
