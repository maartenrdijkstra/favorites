package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Joke;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JokeRepository extends JpaRepository<Joke, Long> {
    public List<Joke> getJokesByUserOrderByIdDesc(User user);
}
