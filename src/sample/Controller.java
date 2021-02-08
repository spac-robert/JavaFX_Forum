package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.Account;
import sample.Repository.UsersRepository;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private Label warningLoginLb;
    private final UsersRepository userRepo = new UsersRepository();
    @FXML
    private TextField userNameTXTField;
    @FXML
    private PasswordField passwordField;

    public void pressButtonLogin(ActionEvent event) throws Exception {
        String name = userNameTXTField.getText();
        Optional<Account> user = userRepo.findUserByName(name);
        if (user.isPresent()) {
            Account account = user.get();
            if (account.getPassword().equals(passwordField.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("forum-home.fxml"));
                Parent root = loader.load();
                ControllerForumHome controllerForumHome = loader.getController();
                controllerForumHome.passAccount(account);
                Scene profile = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(profile);
                window.show();
            }
        }

        warningLoginLb.setVisible(true);
        warningLoginLb.setText("Username or password incorrect.\nPlease try again!");

    }

    public void pressButtonSignUp(ActionEvent event) throws IOException {
        SignUp signUp = new SignUp();
        signUp.run(event);
    }

}
