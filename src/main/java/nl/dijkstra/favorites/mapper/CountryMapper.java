package nl.dijkstra.favorites.mapper;

import nl.dijkstra.favorites.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountryMapper {

    public static List<String> getAllCountries() throws IOException {
        String entireJson = JsonReader.readLocalJsonFile("src/main/resources/static/json/countries.json");
        JSONArray jsonArray = new JSONArray(entireJson);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString("name"))
                .collect(Collectors.toList());
    }
}
