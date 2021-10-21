package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Joke;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.JokeMapper;
import nl.dijkstra.favorites.repository.JokeRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class JokeController {

    @Autowired
    private JokeRepository jokeRepository;

    @GetMapping("/jokes")
    public String getUserJokes(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userJokes", jokeRepository.getJokesByUserOrderByIdDesc(user));
        model.addAttribute("pageTitle", "Favorite jokes");

        return "jokes";
    }

    @PostMapping("/jokes")
    public String addJokeToUser(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam String jokeToAdd, @RequestParam(required = false) String ownJoke) {
        if (jokeToAdd.contains("?")) {
            jokeToAdd = jokeToAdd.replaceAll("[?]", "? ");
        }
        Joke joke = Joke.builder()
                .favoriteJoke(jokeToAdd)
                .user(User.builder()
                        .id(loggedUser.getId())
                        .build())
                .build();
        jokeRepository.save(joke);

        return "redirect:/jokes";
    }

    @PostMapping("/deleteJoke")
    public String deleteJoke(@RequestParam Long id) {
        Joke joke = jokeRepository.findById(id).get();
        jokeRepository.delete(joke);

        return "redirect:/jokes";
    }

    @GetMapping("/devJoke")
    public String getDevJoke(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws IOException {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userJokes", jokeRepository.getJokesByUserOrderByIdDesc(user));
        model.addAttribute("pageTitle", "Favorite jokes");
        model.addAttribute("jokeToAdd", JokeMapper.getDevJoke());

        return "jokes";
    }

    @GetMapping("chuckNorrisJoke")
    public String getChuckNorrisJoke(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws IOException {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userJokes", jokeRepository.getJokesByUserOrderByIdDesc(user));
        model.addAttribute("pageTitle", "Favorite jokes");
        model.addAttribute("jokeToAdd", JokeMapper.getChuckNorrisJoke());

        return "jokes";
    }

    @GetMapping("regularJoke")
    public String getRegularJoke(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws IOException {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userJokes", jokeRepository.getJokesByUserOrderByIdDesc(user));
        model.addAttribute("pageTitle", "Favorite jokes");
        model.addAttribute("jokeToAdd", JokeMapper.getRegularJoke());

        return "jokes";
    }

    @GetMapping("ownJoke")
    public String getOwnJoke(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userJokes", jokeRepository.getJokesByUserOrderByIdDesc(user));
        model.addAttribute("pageTitle", "Favorite jokes");
        model.addAttribute("jokeToAdd", "");

        return "jokes";
    }
}