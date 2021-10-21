package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Users Favorites");

        return "users";
    }

    @GetMapping(value = "viewUsersFavorites")
    public String getUsersFavorites(Model model, @RequestParam("selectedUserId") Long selectedUserId) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Users Favorites");

        User selectedUser = new User();
        Optional<User> userOptional = userRepository.findById(selectedUserId);

        if (userOptional.isPresent()) {
            selectedUser = userOptional.get();
        }

        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("actors", actorRepository.getActorsByUserOrderByIdDesc(selectedUser));
        model.addAttribute("books", bookRepository.getBooksByUserOrderByIdDesc(selectedUser));
        model.addAttribute("countries", countryRepository.getCountriesByUserOrderByIdDesc(selectedUser));
        model.addAttribute("jokes", jokeRepository.getJokesByUserOrderByIdDesc(selectedUser));
        model.addAttribute("movies", movieRepository.getMovieAndTvsByUserOrderByIdDesc(selectedUser));
        model.addAttribute("quotes", quoteRepository.getQuotesByUserOrderByIdDesc(selectedUser));

        return "users";
    }
}
