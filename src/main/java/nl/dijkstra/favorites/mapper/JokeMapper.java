package nl.dijkstra.favorites.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dijkstra.favorites.util.JsonReader;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JokeMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getDevJoke() {
        try {
            String devJokeString = JsonReader.getJsonFirstObjectString("https://backend-omega-seven.vercel.app/api/getjoke");
            Map<String, String> map = mapper.readValue(devJokeString, HashMap.class);

            return map.get("question") + "\n" + map.get("punchline");
        } catch (Exception e) {
            e.printStackTrace();
            return "Debugging: Removing the needles from the haystack.";
        }
    }

    public static String getChuckNorrisJoke() {
        try {
            JSONObject json = JsonReader.readJsonFromUrlWithHeader("https://geek-jokes.sameerkumar.website/api?format=json");
            return json.getString("joke");

        } catch (Exception e) {
            e.printStackTrace();
            return "When Chuck Norris does a pushup, he’s pushing the Earth down.";
        }
    }

    public static String getRegularJoke() {
        try {
            JSONObject json = JsonReader.readJsonFromUrlWithHeader("https://icanhazdadjokes.com");

            return json.getString("joke");
        } catch (Exception e) {
            e.printStackTrace();
            return "What did the fish say when he swam into a wall? Dam";
        }
    }
}
