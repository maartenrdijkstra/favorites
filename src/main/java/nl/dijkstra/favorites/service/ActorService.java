package nl.dijkstra.favorites.service;

import nl.dijkstra.favorites.entity.Actor;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.ActorMapper;
import nl.dijkstra.favorites.repository.ActorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    private static final List<Actor> ACTORS = new ArrayList<>();

    private ActorService() throws IOException {
        List<String> actorNames = ActorMapper.getAllActors();
        for (String name : actorNames) {
            Actor actor = Actor.builder()
                    .name(name)
                    .build();
            ACTORS.add(actor);
        }
    }

    public List<Actor> filterActors(String keyword) {
        List<Actor> filteredActors = new ArrayList<>();
        for (Actor actor : ACTORS) {
            if (actor.getName() != null) {
                if (StringUtils.containsIgnoreCase(actor.getName(), keyword)) {
                    filteredActors.add(actor);
                }
            }
        }
        if (filteredActors.size() == 0) {
            return null;
        }
        Collections.shuffle(filteredActors);
        return filteredActors;
    }

    public List<Actor> getActorsByUser(User user) {
        return actorRepository.getActorsByUserOrderByIdDesc(user);
    }
}
