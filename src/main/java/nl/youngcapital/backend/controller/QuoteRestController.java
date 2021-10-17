package nl.youngcapital.backend.controller;

import nl.youngcapital.backend.entity.Quote;
import nl.youngcapital.backend.exception.ResourceNotFoundException;
import nl.youngcapital.backend.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuoteRestController {

    @Autowired
    private QuoteRepository quoteRepository;

    // Perhaps not necessary
    @GetMapping("/quotes")
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @PostMapping("/quotes")
    public Quote createQuote(@RequestBody Quote quote) {
        return quoteRepository.save(quote);
    }

    // Perhaps obsolete
    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The quote with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(quote);
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteQuote(@PathVariable long id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The quote with id " + id + " currently does not exist as your favorite."));
        quoteRepository.delete(quote);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
