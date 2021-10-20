package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Quote;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.QuoteMapper;
import nl.dijkstra.favorites.repository.QuoteRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("quotes")
    public String getUserQuotes(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userQuotes", quoteRepository.getQuotesByUser(user));
        model.addAttribute("pageTitle", "Favorite quotes");

        return "quotes";
    }

    @PostMapping(value = "quotes")
    public String addQuoteToUser(@RequestParam("favoriteQuote") String favoriteQuote,
                                 @RequestParam("source") String source,
                                 @AuthenticationPrincipal CustomUserDetails loggedUser) {

        Quote quote = Quote.builder()
                .favoriteQuote(favoriteQuote)
                .source(source)
                .user(User.builder()
                        .id(loggedUser.getId())
                        .build())
                .build();

        quoteRepository.save(quote);

        return "redirect:/quotes";
    }

    @PostMapping("/deleteQuote")
    public String deleteQuote(@RequestParam Long id) {
        Quote quote = quoteRepository.findById(id).get();
        quoteRepository.delete(quote);

        return "redirect:/quotes";
    }

    @GetMapping("movieQuote")
    public String getMovieQuote(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws IOException, ParseException {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userQuotes", quoteRepository.getQuotesByUser(user));
        model.addAttribute("pageTitle", "Favorite Quotes");
        Quote quote = QuoteMapper.getRandomMovieQuote();
        model.addAttribute("quoteToAdd", quote);

        return "quotes";
    }

    @GetMapping("wisdomQuote")
    public String getWisdomQuote(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) throws IOException, ParseException {
        User user = User.builder().id(loggedUser.getId()).build();

        model.addAttribute("userQuotes", quoteRepository.getQuotesByUser(user));
        model.addAttribute("pageTitle", "Favorite Quotes");
        Quote quote = QuoteMapper.getWisdomQuote();
        model.addAttribute("quoteToAdd", quote);

        return "quotes";
    }

}
