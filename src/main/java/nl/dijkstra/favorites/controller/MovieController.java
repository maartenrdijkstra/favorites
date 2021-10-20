package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Movie;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.MovieMapper;
import nl.dijkstra.favorites.repository.MovieRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public String getUserMovies(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model, @Param("keyword") String keyword) throws IOException {
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userMovies", movieRepository.getMovieAndTvsByUser(user));
        model.addAttribute("pageTitle", "Favorite movies");
        model.addAttribute("keyword", keyword);

        List<Movie> movies = new ArrayList<>();

        if (keyword != null) {
            movies = MovieMapper.getMovies(keyword);
        }

        model.addAttribute("filteredMovies", movies);

        return "movies";
    }

    @PostMapping(value = "movies")
    public String addMovieToUser(@RequestParam("title") String title,
                                 @RequestParam("year") String year,
                                 @AuthenticationPrincipal CustomUserDetails loggedUser) {
        Movie movie = Movie.builder()
                .title(title)
                .year(year)
                .user(User.builder()
                        .id(loggedUser.getId())
                        .build())
                .build();

        movieRepository.save(movie);

        return "redirect:/movies";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(@RequestParam Long id) {
        Movie movie = movieRepository.findById(id).get();
        movieRepository.delete(movie);

        return "redirect:/movies";
    }
}
