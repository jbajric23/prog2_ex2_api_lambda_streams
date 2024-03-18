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



    @Test
    public void testSortMoviesAscending() throws FileNotFoundException {
        //create dummy movies
        Movie movie1 = new Movie("B Movie", "Description", new ArrayList<>());
        Movie movie2 = new Movie("A Movie", "Description", new ArrayList<>());

        //add movies to HomeController
        HomeController homeController = new HomeController();
        homeController.observableMovies.addAll(movie1, movie2);

        //sorting of movies
        homeController.sortMovies(true);

        //correctly sorted?
        assertEquals("A Movie", homeController.observableMovies.get(0).getTitle());
        assertEquals("B Movie", homeController.observableMovies.get(1).getTitle());
    }

    @Test
    public void filterMovies() throws FileNotFoundException {
        // Create a list of movies
        List<Movie> allMovies = new ArrayList<>();
        allMovies.add(new Movie("Movie 1", "Description 1", Arrays.asList(Genre.ACTION, Genre.DRAMA)));
        allMovies.add(new Movie("Movie 2", "Description 2", Arrays.asList(Genre.COMEDY, Genre.DRAMA)));
        allMovies.add(new Movie("Movie 3", "Description 3", Arrays.asList(Genre.ACTION, Genre.COMEDY)));

        // Create HomeController and set allMovies
        HomeController homeController = new HomeController();
        homeController.setAllMovies(allMovies);

        // Call filterMovies with query and genre
        List<Movie> filteredMovies = homeController.filterMovies("1", Genre.ACTION);

        // Check if the filtered list contains the correct movies
        assertEquals(1, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
    }

    @Test
    public void testSortMoviesDescending() throws FileNotFoundException {
        Movie movie1 = new Movie("A Movie", "Description", new ArrayList<>());
        Movie movie2 = new Movie("B Movie", "Description", new ArrayList<>());

        HomeController homeController = new HomeController();
        homeController.observableMovies.addAll(movie1, movie2);

        homeController.sortMovies(false);

        //correctly sorted?
        assertEquals("B Movie", homeController.observableMovies.get(0).getTitle());
        assertEquals("A Movie", homeController.observableMovies.get(1).getTitle());
    }

    @Test
    public void testSortMoviesWithAlreadySortedMovies() throws FileNotFoundException {
        //does sorting still work if movies are already sorted?
        Movie movie1 = new Movie("A Movie", "Description", new ArrayList<>());
        Movie movie2 = new Movie("B Movie", "Description", new ArrayList<>());

        HomeController homeController = new HomeController();
        homeController.observableMovies.addAll(movie1, movie2);

        homeController.sortMovies(true);

        assertEquals("A Movie", homeController.observableMovies.get(0).getTitle());
        assertEquals("B Movie", homeController.observableMovies.get(1).getTitle());
    }

    @Test
    public void testSortMoviesWithReverseOrderMovies() throws FileNotFoundException {
        //does sorting still work if movies are entered in a different order
        Movie movie1 = new Movie("B Movie", "Description", new ArrayList<>());
        Movie movie2 = new Movie("A Movie", "Description", new ArrayList<>());

        HomeController homeController = new HomeController();
        homeController.observableMovies.addAll(movie1, movie2);

        homeController.sortMovies(true);

        assertEquals("A Movie", homeController.observableMovies.get(0).getTitle());
        assertEquals("B Movie", homeController.observableMovies.get(1).getTitle());
    }

    @Test
    public void testSortMoviesWithSameTitleMovies() throws FileNotFoundException {
        //does sorting still work if movies with the same title are sorted?
        Movie movie1 = new Movie("A Movie", "Description 1", new ArrayList<>());
        Movie movie2 = new Movie("A Movie", "Description 2", new ArrayList<>());

        HomeController homeController = new HomeController();
        homeController.observableMovies.addAll(movie1, movie2);

        homeController.sortMovies(true);

        assertEquals("Description 1", homeController.observableMovies.get(0).getDescription());
        assertEquals("Description 2", homeController.observableMovies.get(1).getDescription());
        /*
         are the same named movies sorted in a way so
         that the ones entered first are sorted first?
        */
    }

    @Test
    public void testSortMoviesWithEmptyList() throws FileNotFoundException {
        // test if sorting function can sort an empty list
        HomeController homeController = new HomeController();

        homeController.sortMovies(true);

        assertEquals(0, homeController.observableMovies.size());
    }
}