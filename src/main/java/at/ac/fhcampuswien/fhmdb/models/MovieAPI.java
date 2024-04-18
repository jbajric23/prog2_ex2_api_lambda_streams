package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MovieAPI {
    private final OkHttpClient client;
    private final Gson gson;

    public MovieAPI() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<Movie> callAPI(String filter) throws IOException {
        List<Movie> movies = new ArrayList<>();
        Request request;
        if (filter != null) {
             request = new Request.Builder()
                    .url("https://prog2.fh-campuswien.ac.at/movies" + filter)
                    .header("User-Agent", "FHMDB-App")
                    .build();
        } else {
            request = new Request.Builder()
                    .url("https://prog2.fh-campuswien.ac.at/movies")
                    .header("User-Agent", "FHMDB-App")
                    .build();
        }
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Movie data could not be loaded. " + response);
            JsonArray jsonArray = gson.fromJson(response.body().string(), JsonArray.class);
            return loadMovies(movies, jsonArray);
        }
    }

    public List<Movie> loadMovies(List<Movie> movies, JsonArray jsonArray) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject movieElement = jsonArray.get(i).getAsJsonObject();
                movies.add(createMovie(movieElement));
            }
        return movies;
    }

    private Movie createMovie(JsonObject movieArray) {
        // Create Movie and attributes needed for the constructor
        String title = movieArray.get("title").getAsString();
        String description = movieArray.get("description").getAsString();
        List<Genre> genres = new ArrayList<>();
        JsonElement genresElement = movieArray.get("genres");
        genres = setGenresToList(genresElement);
        int releaseYear = movieArray.get("releaseYear").getAsInt();
        float rating = movieArray.get("rating").getAsFloat();
        Movie movie = new Movie(title, description, genres, releaseYear, rating);
        // Initialize other attributes
        movie.setLengthInMinutes(movieArray.get("lengthInMinutes").getAsInt());
        movie.setDirectors(setListFromJsonElement(movieArray.get("directors")));
        movie.setWriters(setListFromJsonElement(movieArray.get("writers")));
        movie.setMainCast(setListFromJsonElement(movieArray.get("mainCast")));
        return movie;
    }

    private List<Genre> setGenresToList(JsonElement genresElement) {
        List<Genre> genres = new ArrayList<>();
        if (genresElement.isJsonArray()) {
            JsonArray genresArray = genresElement.getAsJsonArray();
            for (JsonElement genreElement : genresArray) {
                String genreName = genreElement.getAsString();
                Genre genre = Genre.valueOf(genreName);
                genres.add(genre);
            }
        } else {
            String genresString = genresElement.getAsString();
            for (String genreName : genresString.split(",")) {
                Genre genre = Genre.valueOf(genreName);
                genres.add(genre);
            }
        }
        return genres;
    }

    private List<String> setListFromJsonElement(JsonElement element) {
        List<String> list = new ArrayList<>();
        if (element.isJsonArray()) {
            JsonArray jsonArray = element.getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                list.add(jsonElement.getAsString());
            }
        } else {
            list.add(element.getAsString());
        }
        return list;
    }
}
