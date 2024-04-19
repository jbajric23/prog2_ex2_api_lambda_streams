package at.java.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieAPITest {

    MovieAPI movieAPI = new MovieAPI();
    List<Movie> allMovies;

    @Test
    void callAPI_returns_correct_number_of_movies() throws IOException {
        // Given
        int expectedSize = 31;

        // When
        allMovies = movieAPI.callAPI(null);
        int actualSize = allMovies.size();

        // Then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void callAPI_returns_correct_movie_title_of_first_movie() throws IOException {
        // Given
        String expectedTitle = "The Godfather";

        // When
        allMovies = movieAPI.callAPI(null);
        String actualTitle = allMovies.get(0).getTitle();

        // Then
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void callAPI_returns_correct_movie_lengthInMinutes_from_third_Element() throws IOException {
        // Given
        int expectedLengthInMinutes = 152;

        // When
        allMovies = movieAPI.callAPI(null);
        int actualLengthInMinutes = allMovies.get(2).getLengthInMinutes();

        // Then
        assertEquals(expectedLengthInMinutes, actualLengthInMinutes);
    }

    @Test
    void callAPI_returns_correct_movies_when_genre_is_COMERDY() throws IOException {
        // Given
        int expectedSize = 6;

        // When
        allMovies = movieAPI.callAPI("?genre=COMEDY");
        int actualSize = allMovies.size();

        // Then
        assertEquals(expectedSize, actualSize);
    }
}