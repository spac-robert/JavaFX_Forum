package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Repository.PostsRepository;
import sample.Repository.UsersRepository;
import sample.entity.Account;
import sample.entity.Post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

public class ControllerForumHome {
    @FXML
    private TextField searchBox;
    @FXML
    private Label searchLB;
    @FXML
    private Label title;
    @FXML
    private TextField postTitle;
    private final UsersRepository usersRepository = new UsersRepository();
    @FXML
    private ListView<String> listView;
    private Account userAccount;
    @FXML
    private TextArea postArea;
    @FXML
    private Button postBtn;
    public void passAccount(Account account) {
        this.userAccount = account;
    }

    private final PostsRepository postsRepository = new PostsRepository();

    public void populateListView(Vector<Account> allAccounts) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < allAccounts.size(); i++) {
            data.add(allAccounts.elementAt(i).getUserName());
        }
        listView.setPrefHeight(allAccounts.size() * 24 + 2);
        listView.setItems(data);
        //Make a scrollbar for ViewList

    }

    public void searchKeyReleased() throws SQLException {
        listView.setVisible(true);
        searchLB.setVisible(false);
        String name = searchBox.getText();
        Vector<Account> allAccounts = usersRepository.allNamesStartingWithAValue(name);
        populateListView(allAccounts);
    }

    public void writeTitle() {
        title.setVisible(false);
    }

    public void makeDefault() throws SQLException {
        searchLB.setVisible(true);
        searchBox.setText("");
        listView.setVisible(false);
        title.setVisible(postTitle.getText().isEmpty());
        postBtn.setStyle("-fx-text-fill: #000000");

    }

    public void selectItemViewList(javafx.scene.input.MouseEvent event) throws IOException, SQLException {
        String name = listView.getSelectionModel().getSelectedItem();
        Optional<Account> account = usersRepository.findUserByName(name);
        if (account.isPresent()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = loader.load();
            ControllerProfile controllerProfile = loader.getController();
            controllerProfile.profileUsername(userAccount, account.get());
            Scene profile = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(profile);
            window.show();
        }
    }

    public void profileButtonPressed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        ControllerProfile controllerProfile = loader.getController();
        controllerProfile.profileUsername(userAccount, userAccount);
        Scene profile = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(profile);
        window.show();
    }

    public void actionButtonPost(ActionEvent event) throws SQLException {
        String getTitle, getPost;
        getTitle = postTitle.getText();
        getPost = postArea.getText();
        if (!getTitle.isEmpty() && !getPost.isEmpty()) {
            Post post = new Post("", userAccount.getId(), getTitle, getPost);
            postsRepository.insertData(post);
        }
        else {
            postBtn.setStyle("-fx-text-fill: #ff0000");
        }

        postArea.setText("");
        postTitle.setText("");
    }

}
//Trebuie sa se genereze cate un pane cu fiecare postare in josul celui precedent(cred ca merge un flowpane si/ sau scrol pane)
//de facut un pane cu un label pt afisarea mesajului,buton pentru delete(butonul apare doar daca userul curent este cel
// ce a postat, verificare id user cu id user postare sau daca este admin)

