package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Country;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.repository.CountryRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import nl.dijkstra.favorites.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @PostMapping("/deleteCountry")
    public String deleteCountry(@RequestParam Long id) {
        Country country = countryRepository.findById(id).get();
        countryRepository.delete(country);

        return "redirect:/countries";
    }

    @GetMapping("countries")
    public String getCountries(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model, @Param("keyword") String keyword) {
        model.addAttribute("pageTitle", "Favorite countries");
        model.addAttribute("keyword", keyword);
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userCountries", countryService.getCountriesByUser(user));

        if (keyword != null) {
            List<Country> countries = countryService.filterCountries(keyword);
            if (countries.size() > 0) {
                model.addAttribute("filteredCountries", countries);
            }
        }

        return "countries";
    }

    @PostMapping(value = "countries")
    public String addCountryToUser(@RequestParam("name") String name,
                                   @AuthenticationPrincipal CustomUserDetails loggedUser) {
        Country countryToAdd = Country.builder()
                .name(name)
                .user(User.builder().id(loggedUser.getId()).build())
                .build();
        countryRepository.save(countryToAdd);

        return "redirect:/countries";
    }
}