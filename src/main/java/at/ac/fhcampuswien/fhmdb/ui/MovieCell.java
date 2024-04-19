package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label releaseYear = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Label rating = new Label();
    private final Label director = new Label();
    private final Label mainCast = new Label();
    private final Label writers = new Label();
    private final Label lengthInMinutes = new Label();
    private final ImageView movieImage = new ImageView();
    private final HBox layout = new HBox();
    private final VBox textLayout = new VBox();
    private static final Map<String, Image> imageCache = new HashMap<>();

    public MovieCell() {
        movieImage.setFitWidth(100);
        movieImage.setPreserveRatio(true);
        layout.getChildren().addAll(movieImage, textLayout);
        textLayout.setSpacing(4);  // spacing between text items
    }



    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            updateTextLayout(movie);
            loadImage(movie.getImgUrl());
            setGraphic(layout);
        }
    }
    private void updateTextLayout(Movie movie) {
        textLayout.getChildren().clear();
        textLayout.getChildren().addAll(
                new Label(movie.getTitle()),
                new Label("Director: " + String.join(", ", movie.getDirectors())),
                new Label("Main Cast: " + String.join(", ", movie.getMainCast())),
                new Label("Release: " + movie.getReleaseYear()),
                new Label("Rating: " + String.format("%.2f", movie.getRating())),
                new Label("Length: " + movie.getLengthInMinutes() + " min"),
                new Label("Genres: " + String.join(", ", movie.getGenres().toString()))
        );
    }

    private void loadImage(String imageUrl) {
        Image image = imageCache.get(imageUrl);
        if (image == null) {
            image = new Image(imageUrl, true);  // Load in background
            imageCache.put(imageUrl, image);
        }
        movieImage.setImage(image);
    }
    }


