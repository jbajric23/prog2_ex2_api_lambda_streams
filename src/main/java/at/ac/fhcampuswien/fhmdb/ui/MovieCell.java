package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    private final HBox directorAndCast = new HBox(director, mainCast, writers); // HBox for director, cast and writers
    private final BorderPane titleAndRating = new BorderPane();
    private final HBox releaseYearAndLength = new HBox(releaseYear, lengthInMinutes); // HBox for release year and length in minutes
    private final VBox attributes = new VBox(titleAndRating, directorAndCast, detail, releaseYearAndLength); // VBox for other attributes
    private final HBox layout = new HBox(movieImage, attributes); // HBox for image and other attributes


    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            releaseYear.setText(" Release: " + movie.getReleaseYear());
            director.setText("Director: " + String.join(", ", movie.getDirectors()));
            mainCast.setText("Main Cast: " + String.join(", ", movie.getMainCast()));
            writers.setText("Writers: " + String.join(", ", movie.getWriters()));
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            genres.setText(
                    movie.getGenres() != null
                            ? movie.getGenres().toString()
                            : "No genres available"
            );
            rating.setText("Rating: " + String.format("%.2f", movie.getRating())); // Format the rating value
            lengthInMinutes.setText("Length: " + movie.getLengthInMinutes() + " minutes");
            movieImage.setImage(new Image(movie.getImgUrl()));
            movieImage.setImage(new Image(movie.getImgUrl()));
            movieImage.setFitWidth(100); // Set the width of the image
            movieImage.setPreserveRatio(true); // Preserve the aspect ratio
            titleAndRating.setLeft(title); // Set title to the left
            titleAndRating.setRight(rating); // Set rating to the right


            // color scheme
            title.getStyleClass().add("text-yellow");
            title.setFont(title.getFont().font(24)); // Set the font size of the title

            releaseYear.getStyleClass().add("text-lightgrey");
            director.getStyleClass().add("text-lightgrey");
            mainCast.getStyleClass().add("text-lightgrey");
            writers.getStyleClass().add("text-lightgrey");

            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-lightgrey");
            rating.getStyleClass().add("text-lightgrey");
            lengthInMinutes.getStyleClass().add("text-lightgrey");


            // Remove the brackets from the genres text
            String genresText = genres.getText();
            genresText = genresText.replace("[", "");
            genresText = genresText.replace("]", "");
            genres.setText(genresText);

            // Set spacing for HBoxes
            directorAndCast.setSpacing(20);
            releaseYearAndLength.setSpacing(20);
            attributes.setSpacing(20);
            layout.setSpacing(20);

            layout.setSpacing(20);
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

