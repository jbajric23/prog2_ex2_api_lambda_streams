package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Movie {
    private String title;
    private String description;

    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(FileReader fileReader){

        List<Movie> movies = new ArrayList<>();
        // Read the resource file with the movie data
        try (BufferedReader br = new BufferedReader(fileReader))
        {
            String line;
            // True as long as there are lines to read
            while ((line = br.readLine()) != null) {
                // Assuming ";" separates parts of the movie data
                String[] parts = line.split(";");
                String title = parts[0];
                String description = parts[1];
                // "," splits different genres
                String[] genreNames = parts[2].split(",");
                List<Genre> genres = new ArrayList<>();
                // Convert genre names to Genre enum values with a for loop
                for (String genreName : genreNames) {
                    Genre genre = Genre.valueOf(genreName);
                    genres.add(genre);
                }
                // Add the object movie to the list
                movies.add(new Movie(title, description, genres));
            }
        } catch (IllegalArgumentException e) {
            // Catch block if illegal argument is passed to Genre.valueOf
            throw new IllegalArgumentException("Genre not found in enum");
        } catch (IOException e) {
            // Catch block if there is an error reading the file
            throw new RuntimeException("Error reading file");
        } catch (Exception e) {
            // Catch block if there occurs another error
            e.printStackTrace();
        }
        return movies;
    }


}
