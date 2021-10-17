package nl.youngcapital.backend.controller;

import nl.youngcapital.backend.entity.Country;
import nl.youngcapital.backend.exception.ResourceNotFoundException;
import nl.youngcapital.backend.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CountryRestController {

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
