package at.ac.fhcampuswien.fhmdb;


import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetFilterBtn;

    public List<Movie> allMovies;
    // Call initializeMovies method with a FileReader object as parameter for better testing possibilities
    {
        try {
            allMovies = Movie.initializeMovies(new FileReader("src/main/resources/at/ac/fhcampuswien/fhmdb/DummyMovies.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> filterMovies(String query, Genre genre) {
        return allMovies.stream()
                .filter(movie -> (query == null || movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        movie.getDescription().toLowerCase().contains(query.toLowerCase())) &&
                        (genre == null || movie.getGenres().contains(genre)))
                .collect(Collectors.toList());
    }

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public HomeController() throws FileNotFoundException {
    }

    public void setAllMovies(List<Movie> allMovies) {
            this.allMovies = allMovies;
    }


    // sort movies via comparison of titles
    public void sortMovies(boolean ascending) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        FXCollections.sort(observableMovies, comparator);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adds all dummy data movies to the observable list
        observableMovies.addAll(allMovies);


        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // Add genre items to the genreComboBox
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        // TODO add event handlers to buttons and call the regarding methods

        searchBtn.setOnAction(actionEvent -> {
            String query = searchField.getText();
            Genre genre = (Genre) genreComboBox.getSelectionModel().getSelectedItem();
            List<Movie> filteredMovies = filterMovies(query, genre);
            observableMovies.clear();
            observableMovies.addAll(filteredMovies);

            //possibility to sort a filtered selection of the movies
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortMovies(true);
            } else {
                sortMovies(false);
            }
        });

        resetFilterBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(allMovies);

            searchField.clear();
        });

        // Sort button for listing the movies in alphabetical order; either descending or ascending
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortMovies(true);
                sortBtn.setText("Sort (desc)");
            } else {
                sortMovies(false);
                sortBtn.setText("Sort (asc)");
            }
        });


    }
}