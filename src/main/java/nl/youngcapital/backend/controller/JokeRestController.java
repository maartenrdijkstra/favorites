package nl.youngcapital.backend.controller;

import nl.youngcapital.backend.entity.Joke;
import nl.youngcapital.backend.exception.ResourceNotFoundException;
import nl.youngcapital.backend.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JokeRestController {

    @Autowired
    private JokeRepository jokeRepository;

    // Perhaps not necessary
    @GetMapping("/jokes")
    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    @PostMapping("/jokes")
    public Joke createJoke(@RequestBody Joke joke) {
        return jokeRepository.save(joke);
    }

    // Perhaps obsolete
    @GetMapping("/jokes/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        Joke joke = jokeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Joke with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(joke);
    }

    @DeleteMapping("/jokes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJoke(@PathVariable long id) {
        Joke joke = jokeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Joke with id " + id + " currently does not exist as your favorite."));
        jokeRepository.delete(joke);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
