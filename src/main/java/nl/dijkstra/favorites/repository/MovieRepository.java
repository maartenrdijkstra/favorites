package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Movie;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie>  getMovieAndTvsByUserOrderByIdDesc(User user);
}
