package nl.dijkstra.favorites;

import nl.dijkstra.favorites.mapper.BookMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NewTest {

    @Test
    public void printObject() throws IOException {

        System.out.println(BookMapper.getBooks("Harry"));
    }
}
