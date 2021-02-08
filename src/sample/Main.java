package sample;

import Tests.UsersRepositoryTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Login login = new Login();
        login.displayLogin(primaryStage);
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
