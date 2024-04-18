package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Movie {

    private String title;
    private String description;

    private int releaseYear; // new attribute
    private float rating; // new attribute

    private int lengthInMinutes; // new attribute

    private List<Genre> genres;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;

    public Movie(String title,String description,List<Genre> genres, int releaseYear, float rating){
        this.title = title;
        this.description = description;
        this.genres = genres;
        // Check if new attributes should be initialized here
        this.releaseYear = releaseYear;// initialize new attribute
        this.rating = rating; // initialize new attribute
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

    // getters for new attributes
    public int getReleaseYear() {
        return releaseYear;
    }

    public float getRating() {
        return rating;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    /*
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
*/

}
