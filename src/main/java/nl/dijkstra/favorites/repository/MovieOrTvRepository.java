package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieOrTvRepository extends JpaRepository<Actor, Long> {
}
