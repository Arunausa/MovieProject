package bd.edu.seu.movieproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(AnchorPane page, String fxmlName){
        try {
            AnchorPane loader =
                    FXMLLoader.load(Objects.requireNonNull(MovieApplication.class.getResource(fxmlName)));
            page.getChildren().removeAll();
            page.getChildren().setAll(loader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
