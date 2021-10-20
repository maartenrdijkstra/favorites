package nl.dijkstra.favorites.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dijkstra.favorites.entity.Quote;
import nl.dijkstra.favorites.util.JsonReader;

import java.io.IOException;
import java.util.*;

import static nl.dijkstra.favorites.util.JsonReader.readLocalJsonFile;

public class QuoteMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Quote getRandomMovieQuote() throws IOException {
        String jsonArray = readLocalJsonFile("src/main/resources/static/json/movie-quotes.json");

        List<Map<String, String>> listMap = mapper.readValue(jsonArray, List.class);
        Random rand = new Random();
        Map<String, String> randomQuote = listMap.get(rand.nextInt(listMap.size()));


        return Quote.builder()
                .favoriteQuote("\"" + randomQuote.get("quote") + "\"")
                .source(randomQuote.get("author") + " (" + randomQuote.get("source") + ")")
                .build();
    }

    public static Quote getWisdomQuote() throws IOException {
        try {
            String json = JsonReader.getRandomJsonObjectString("https://type.fit/api/quotes");
            Map<String, String> map = mapper.readValue(json, HashMap.class);
        return Quote.builder()
                .favoriteQuote("\"" + map.get("text") + "\"")
                .source(map.get("author"))
                .build();
        } catch (Exception e) {
            return Quote.builder()
                    .favoriteQuote("Nothing is impossible, the word itself says 'I'm possible'!")
                    .source("Audrey Hepburn")
                    .build();
        }
    }
}
