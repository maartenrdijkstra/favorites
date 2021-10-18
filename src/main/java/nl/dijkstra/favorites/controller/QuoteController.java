package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Quote;
import nl.dijkstra.favorites.repository.QuoteRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/quotes")
    public List<Quote> getUserJokes(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

        return quoteRepository.findAll();
    }

}
