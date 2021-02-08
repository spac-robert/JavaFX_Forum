package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.Account;
import sample.Repository.UsersRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ControllerSignUp {
    @FXML
    private Label warningUsername;
    @FXML
    private Label warningPassword;
    @FXML
    private final UsersRepository userRepo = new UsersRepository();
    @FXML
    private TextField userNameTXTField;
    @FXML
    private PasswordField passwordField;

    @FXML

    //sa fac un validator ce arunca exceptii si o sa le prinda aceste warning uri

    public void signUpButtonAction(ActionEvent event) throws IOException, SQLException {
        String name = userNameTXTField.getText();
        String password = passwordField.getText();

        if (name.equals("")) {
            warningUsername.setVisible(true);
            warningUsername.setText("Username can't be blank");
            return;
        }

        if (password.equals("")) {
            warningPassword.setVisible(true);
            warningPassword.setText("Password must have 8 letters");
            return;
        }

        Optional<Account> account = userRepo.findUserByName(name);
//        account.ifPresentOrElse(acc -> {
//            warningUsername.setVisible(true);
//            warningUsername.setText("Username already taken");
//        }, () -> {
//            Login login = new Login();
//            login.displayLogin((Stage) ((Node) event.getSource()).getScene().getWindow());
//        });

        if(account.isPresent()) {
            warningUsername.setVisible(true);
            warningUsername.setText("Username already taken");
            return;
        }
        Account account1 = new Account("",name,password,"user");
        userRepo.insertData(account1);
        Login login = new Login();
        login.displayLogin((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

}
