module bd.edu.seu.movieproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // for postgresql


    opens bd.edu.seu.movieproject to javafx.fxml;
    exports bd.edu.seu.movieproject;
}