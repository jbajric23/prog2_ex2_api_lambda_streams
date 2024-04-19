package at.java.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class HomeControllerTest {
 /*
    @Test
    public void testGetMostPopularActor() throws FileNotFoundException {
        HomeController controller = new HomeController();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie1", "Actor1", 2000, "Director1", 7.0),
                new Movie("Movie2", "Actor1", 2001, "Director2", 8.0),
                new Movie("Movie3", "Actor2", 2002, "Director3", 9.0)
        );
        String result = controller.getMostPopularActor(movies);
        assertEquals("Actor1", result);
    }

    @Test
    public void testGetLongestMovieTitle() throws FileNotFoundException {
        HomeController controller = new HomeController();
        List<Movie> movies = Arrays.asList(
                new Movie("Short Title", "Actor1", 2000, "Director1", 7.0),
                new Movie("The Longest Movie Title That Could Possibly Exist", "Actor2", 2001, "Director2", 8.0),
                new Movie("Medium Title", "Actor3", 2002, "Director3", 9.0)
        );
        String result = controller.getLongestMovieTitle(movies);
        assertEquals("The Longest Movie Title That Could Possibly Exist", result);
    }

    @Test
    public void testCountMoviesFrom() throws FileNotFoundException {
        HomeController controller = new HomeController();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie1", "Actor1", 2000, "Director1", 7.0),
                new Movie("Movie2", "Actor2", 2001, "Director1", 8.0),
                new Movie("Movie3", "Actor3", 2002, "Director2", 9.0)
        );
        long result = controller.countMoviesFrom(movies, "Director1");
        assertEquals(2, result);
    }

    @Test
    public void testGetMoviesBetweenYearsFromAPI() throws FileNotFoundException {
        HomeController controller = new HomeController();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie1", "Actor1", 2000, "Director1", 7.0),
                new Movie("Movie2", "Actor2", 2001, "Director2", 8.0),
                new Movie("Movie3", "Actor3", 2002, "Director3", 9.0)
        );
        List<Movie> result = controller.getMoviesBetweenYearsFromAPI(movies, 2000, 2001);
        assertEquals(2, result.size());
    }
*/
}
