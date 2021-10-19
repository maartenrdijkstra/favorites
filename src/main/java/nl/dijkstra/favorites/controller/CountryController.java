package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Country;
import nl.dijkstra.favorites.exception.ResourceNotFoundException;
import nl.dijkstra.favorites.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    // Perhaps not necessary
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @PostMapping("/countries")
    public Country createCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    // Perhaps obsolete
    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " currently does not exist as your favorite."));
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCountry(@PathVariable long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " currently does not exist as your favorite."));
        countryRepository.delete(country);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
