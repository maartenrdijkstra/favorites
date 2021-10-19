package nl.dijkstra.favorites;

import nl.dijkstra.favorites.mapper.ActorMapper;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NewTest {

    @Test
    public void printObject() throws IOException, ExecutionException, InterruptedException, ParseException {
//        System.out.println(JsonReader.readLocalJsonFile("src/main/resources/static/json/movie-quotes.json"));

        System.out.println(ActorMapper.getAllActors());

    }



}
