package bd.edu.seu.movieproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieDashboardController implements Initializable {

    @FXML
    private AnchorPane main_panel;

    public void addMovieInformation(ActionEvent actionEvent) {
        new SceneSwitch(main_panel,"add-movie_view.fxml");
    }

    public void viewMovieInformation(ActionEvent actionEvent) {
        new SceneSwitch(main_panel, "movie-list_view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
