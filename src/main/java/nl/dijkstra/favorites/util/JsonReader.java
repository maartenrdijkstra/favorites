package nl.dijkstra.favorites.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;

public class JsonReader {

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonFromUrlWithHeader(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Accept", "application/json");
        myURLConnection.setRequestMethod("GET");

        InputStream inputStream = myURLConnection.getInputStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String jsonText = JsonReader.readAll(rd);
        return new JSONObject(jsonText);
    }

    public static JSONArray readJsonArrayFromUrl(String urlStr) throws IOException, JSONException {
        URL url = new URL(urlStr);
        HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Accept", "application/json");
        myURLConnection.setRequestMethod("GET");

        InputStream inputStream = myURLConnection.getInputStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            inputStream.close();
        }
    }

    public static String getJsonFirstObjectString(String url) throws IOException {
        JSONArray jsonArray = readJsonArrayFromUrl(url);
        return jsonArray.get(0).toString();
    }

    public static String readLocalJsonFile(String resource) throws IOException {
        Reader reader = new FileReader(resource);

        String line;
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader bufferreader = new BufferedReader(reader);
            while ((line = bufferreader.readLine()) != null) {
                result.append(line);
                result.append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    public static String getRandomJsonObjectString(String url) throws IOException {
        JSONArray jsonArray = readJsonArrayFromUrl(url);
        Random r = new Random();
        return jsonArray.get(r.nextInt(jsonArray.length())).toString();
    }
}