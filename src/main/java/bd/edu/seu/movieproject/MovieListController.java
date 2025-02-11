package bd.edu.seu.movieproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieListController implements Initializable {

    @FXML
    private TableView<Movie> movie_tableview;

    @FXML
    private TableColumn<Movie, Integer> movie_id_col;
    @FXML
    private TableColumn<Movie, String> movie_name_col;
    @FXML
    private TableColumn<Movie, String> movie_category_col;
    @FXML
    private TableColumn<Movie, String> movie_country_col;
    @FXML
    private TableColumn<Movie, LocalDate> movie_release_date_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // database connection
        Connection connection = getConnection();
        // table cel initialization
        tableCellInitialization();
        // data get from database
        viewData(connection);
    }

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBInformation.URL, DBInformation.USERNAME, DBInformation.PASSWORD);
            System.out.println("Connection setup complete!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    private void tableCellInitialization(){
        // mapping between (table col <----> model class variable)
        movie_id_col.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movieID"));
        movie_name_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieName"));
        movie_category_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieCategory"));
        movie_release_date_col.setCellValueFactory(new PropertyValueFactory<Movie, LocalDate>("movieReleaseDate"));
        movie_country_col.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieCountry"));
    }

    private void viewData(Connection connection){
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Movie> movieList = null;
        try {
            String selectScript = "SELECT * FROM public.movie_info";
            statement = connection.prepareStatement(selectScript);
            result = statement.executeQuery();
            movieList = new ArrayList<>();
            while (result.next()){
                LocalDateTime updateAt = null;
                int _id = result.getInt("movie_id");
                String name = result.getString("movie_name");
                String category = result.getString("movie_category");
                LocalDate mDate = LocalDate.parse(result.getDate("movie_release_date").toString());
                String country = result.getString("movie_country");
                LocalDateTime createAt = result.getTimestamp("create_at").toLocalDateTime();
                if (result.getTimestamp("update_at") != null)
                    updateAt = result.getTimestamp("update_at").toLocalDateTime();

                movieList.add(new Movie(_id,name,category,mDate,country,createAt,updateAt));
            }

            // add movieList info into table
            movie_tableview.getItems().addAll(movieList);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }

        }
    }
}
