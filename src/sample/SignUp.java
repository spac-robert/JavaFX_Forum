package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp {

    public void run(ActionEvent event) throws IOException {
        this.displaySignUp(event);
    }

    public void displaySignUp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
        Scene homePage = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homePage);
        window.show();
    }


}
