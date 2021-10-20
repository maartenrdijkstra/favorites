package nl.dijkstra.favorites.mapper;

import nl.dijkstra.favorites.entity.Book;
import nl.dijkstra.favorites.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    public static List<Book> getBooks(String query) throws IOException {
        query = query.replaceAll(" ", "+");
        JSONObject object = JsonReader.readJsonFromUrl(BASE_URL + "?q=" + query);
        String items = object.optString("items");
        JSONArray array = new JSONArray(items);
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject itemObject = array.getJSONObject(i);
            String volumeInfo = itemObject.optString("volumeInfo");
            JSONObject volumeInfoJson = new JSONObject(volumeInfo);

            String title = volumeInfoJson.optString("title");
            JSONArray jsonArray;

            try {
                jsonArray = volumeInfoJson.getJSONArray("authors");
            } catch (Exception e) {
                jsonArray = new JSONArray();
            }

            List<String> authors = new ArrayList<>();

            for (int j = 0; j < jsonArray.length(); j++) {
                authors.add(jsonArray.getString(j));
            }

            String authorResult = "";

            if (authors.size() > 0) {
                authorResult = authors.get(0);
            }
            //Select a maximum of 2 authors
            if (authors.size() > 1) {
                authorResult = authorResult.concat(", " + authors.get(1));
            }

            books.add(Book.builder()
                    .title(title)
                    .author(authorResult)
                    .build());
        }
        return books;
    }
}
