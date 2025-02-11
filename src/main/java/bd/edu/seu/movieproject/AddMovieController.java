package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.*;

public class AddMovieController implements Initializable {

    @FXML
    private TextField tf_movie_name;
    @FXML
    private ComboBox<String> cb_movie_category;
    @FXML
    private DatePicker date_movie_release;
    @FXML
    private ComboBox<String> cb_movie_country;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> movieCategoryList = new ArrayList<>();
        movieCategoryList.add("Select One");
        movieCategoryList.add("Adventure");
        movieCategoryList.add("Crime");
        movieCategoryList.add("SiFi");
        movieCategoryList.add("Romantic");

        List<String> countryList = new ArrayList<>();
        countryList.add("Select One");
        countryList.add("Bangladesh");
        countryList.add("UK");
        countryList.add("USA");

        cb_movie_category.getItems().addAll(movieCategoryList);
        cb_movie_country.getItems().addAll(countryList);

        cb_movie_country.getSelectionModel().selectFirst();
        cb_movie_category.getSelectionModel().selectFirst();
    }
    public void saveMovieInformation(ActionEvent actionEvent) {
        /**
         * Database connection setup
         */

        String URL = "jdbc:postgresql://localhost:5432/movie";
        try {
            Connection db = DriverManager.getConnection(URL, "postgres", "mahb12345");
            System.out.println("Connection setup complete!");

            /**
             * Data Collection
             */
            String movieName = tf_movie_name.getText();
            String movieCategory = cb_movie_category.getValue();
            Date releaseDate = Date.valueOf(date_movie_release.getValue());
            String country = cb_movie_country.getValue();

            /**
             * insert those values into database:
             * 1. statement prepare from connection
             */
            String insertScript = "INSERT INTO public.movie_info(movie_name, movie_category, movie_release_date, movie_country) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = db.prepareStatement(insertScript);
            statement.setString(1, movieName);
            statement.setString(2, movieCategory);
            statement.setDate(3, releaseDate);
            statement.setString(4, country);

            statement.executeUpdate();

            System.out.println("data inserted!!!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Connection setup failed!!");
        }finally {

        }


    }
}
