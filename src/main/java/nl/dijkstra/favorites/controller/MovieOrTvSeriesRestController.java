package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.MovieOrTvSeries;
import nl.dijkstra.favorites.exception.ResourceNotFoundException;
import nl.dijkstra.favorites.repository.MovieOrTvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieOrTvSeriesRestController {

    @Autowired
    private MovieOrTvSeriesRepository movieOrTvSeriesRepository;

    // Perhaps not necessary
    @GetMapping("/movieOrTvSeriess")
    public List<MovieOrTvSeries> getAllMovieOrTvSeriess() {
        return movieOrTvSeriesRepository.findAll();
    }

    @PostMapping("/movieOrTvSeriess")
    public MovieOrTvSeries createMovieOrTvSeries(@RequestBody MovieOrTvSeries movieOrTvSeries) {
        return movieOrTvSeriesRepository.save(movieOrTvSeries);
    }

    // Perhaps obsolete
    @GetMapping("/movieOrTvSeriess/{id}")
    public ResponseEntity<MovieOrTvSeries> getMovieOrTvSeriesById(@PathVariable Long id) {
        MovieOrTvSeries movieOrTvSeries = movieOrTvSeriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The movie or tv series with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(movieOrTvSeries);
    }

    @DeleteMapping("/movieOrTvSeriess/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMovieOrTvSeries(@PathVariable long id) {
        MovieOrTvSeries movieOrTvSeries = movieOrTvSeriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The movie or tv series with id " + id + " currently does not exist as your favorite."));
        movieOrTvSeriesRepository.delete(movieOrTvSeries);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
