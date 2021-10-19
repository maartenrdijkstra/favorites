package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Actor;
import nl.dijkstra.favorites.exception.ResourceNotFoundException;
import nl.dijkstra.favorites.repository.MovieOrTvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieOrTvController {

    @Autowired
    private MovieOrTvRepository movieOrTvRepository;

    // Perhaps not necessary
    @GetMapping("/movieOrTvSeriess")
    public List<Actor> getAllMovieOrTvSeriess() {
        return movieOrTvRepository.findAll();
    }

    @PostMapping("/movieOrTvSeriess")
    public Actor createMovieOrTvSeries(@RequestBody Actor actor) {
        return movieOrTvRepository.save(actor);
    }

    // Perhaps obsolete
    @GetMapping("/movieOrTvSeriess/{id}")
    public ResponseEntity<Actor> getMovieOrTvSeriesById(@PathVariable Long id) {
        Actor actor = movieOrTvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The movie or tv series with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(actor);
    }

    @DeleteMapping("/movieOrTvSeriess/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMovieOrTvSeries(@PathVariable long id) {
        Actor actor = movieOrTvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The movie or tv series with id " + id + " currently does not exist as your favorite."));
        movieOrTvRepository.delete(actor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
