package nl.dijkstra.favorites.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dijkstra.favorites.util.JsonReader;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JokeMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getDevJoke() throws IOException {
        try {
            String devJokeString = JsonReader.getJsonFirstObjectString("https://backend-omega-seven.vercel.app/api/getjoke");
            Map<String, String> map = mapper.readValue(devJokeString, HashMap.class);

            return map.get("question") + "\n" + map.get("punchline");
        } catch (Exception e) {
            return "Debugging: Removing the needles from the haystack.";
        }
    }

    public static String getChuckNorrisJoke() throws IOException {
        try {
            String chuckNorrisJokeString = JsonReader.getJsonObjectString("https://geek-jokes.sameerkumar.website/api?format=json");
            Map<String, String> map = mapper.readValue(chuckNorrisJokeString, HashMap.class);
            return map.get("joke");
        } catch (Exception e) {
            return "When Chuck Norris does a pushup, he’s pushing the Earth down.";
        }
    }

    public static String getRegularJoke() throws IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://icanhazdadjoke.com");
            String json = "{\"id\":\"CIt49pjG6wc\",\"joke\":\"Why don’t skeletons ever go trick or treating? Because they have nobody to go with..com\", \"status\": 200}";
            StringEntity entity = new StringEntity(json);
            httpGet.setEntity(entity);
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-Type", "application/json");

            CloseableHttpResponse response = client.execute(httpGet);
            client.close();

            Scanner s = new Scanner(response.getEntity().getContent()).useDelimiter("//A");
            String result = s.hasNext() ? s.next() : "";

            Map<String, String> map = mapper.readValue(result, HashMap.class);

            return map.get("joke");
        } catch (Exception e) {
            return "What did the fish say when he swam into a wall? Dam";
        }
    }
}
