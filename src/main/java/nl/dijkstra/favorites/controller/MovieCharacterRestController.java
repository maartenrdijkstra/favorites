package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.MovieCharacter;
import nl.dijkstra.favorites.exception.ResourceNotFoundException;
import nl.dijkstra.favorites.repository.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieCharacterRestController {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    // Perhaps not necessary
    @GetMapping("/movieCharacters")
    public List<MovieCharacter> getAllMovieCharacter() {
        return movieCharacterRepository.findAll();
    }

    @PostMapping("/movieCharacters")
    public MovieCharacter createMovieCharacter(@RequestBody MovieCharacter movieCharacter) {
        return movieCharacterRepository.save(movieCharacter);
    }

    // Perhaps obsolete
    @GetMapping("/movieCharacters/{id}")
    public ResponseEntity<MovieCharacter> getMovieCharacterById(@PathVariable Long id) {
        MovieCharacter movieCharacter = movieCharacterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovieCharacter with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(movieCharacter);
    }

    @DeleteMapping("/movieCharacters/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMovieCharacter(@PathVariable long id) {
        MovieCharacter movieCharacter = movieCharacterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovieCharacter with id " + id + " currently does not exist as your favorite."));
        movieCharacterRepository.delete(movieCharacter);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
