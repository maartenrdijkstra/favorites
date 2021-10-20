package nl.dijkstra.favorites.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dijkstra.favorites.entity.Movie;
import nl.dijkstra.favorites.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MovieMapper {

    private static final String BASE_URL = "https://omdbapi.com/?s=";
    private static final ObjectMapper mapper = new ObjectMapper();

    private static String getMoviesKey() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/keys.properties");
        Properties properties = new Properties();
        properties.load(input);

        return properties.getProperty("MOVIES_API_KEY");
    }

    private static String getMoviesRequestUrl(String query) throws IOException {
        return BASE_URL + query + "&type=movie&apikey=" + getMoviesKey();
    }

    public static List<Movie> getMovies(String query) throws IOException {
        query = query.replaceAll(" ", "+");
        JSONObject jsonObject = JsonReader.readJsonFromUrl(getMoviesRequestUrl(query));
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("Search");
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                movies.add(Movie.builder()
                        .title(jsonObj.getString("Title"))
                        .year(jsonObj.getString("Year"))
                        .build());
            }
            return movies;
        } catch (RuntimeException e) {
            return null;
        }
    }
}