package nl.dijkstra.favorites;

import nl.dijkstra.favorites.mapper.JokeMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NewTest {

    @Test
    public void printObject() throws IOException, ExecutionException, InterruptedException {
        JokeMapper jokeMapper = new JokeMapper();

        jokeMapper.getRegularJoke();
    }



}
