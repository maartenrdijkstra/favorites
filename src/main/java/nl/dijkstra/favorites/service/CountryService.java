package nl.dijkstra.favorites.service;

import nl.dijkstra.favorites.entity.Country;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.CountryMapper;
import nl.dijkstra.favorites.repository.CountryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    private static final List<Country> COUNTRIES = new ArrayList<>();

    private CountryService() throws IOException {
        List<String> countryNames = CountryMapper.getAllCountries();
        for (String name : countryNames) {
            Country country = Country.builder()
                    .name(name)
                    .build();
            COUNTRIES.add(country);
        }
    }

    public List<Country> filterCountries(String keyword) {
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : COUNTRIES) {
            if (country.getName() != null) {
                if (StringUtils.containsIgnoreCase(country.getName(), keyword)) {
                    filteredCountries.add(country);
                }
            }
        }
        return filteredCountries;
    }

    public List<Country> getCountriesByUser(User user) {
        return countryRepository.getCountriesByUser(user);
    }
}