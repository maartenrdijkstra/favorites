package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.Actor;
import nl.dijkstra.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    public List<Actor> getActorsByUser(User user);
}
