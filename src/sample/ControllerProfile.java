package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Repository.PostsRepository;
import sample.entity.Account;
import sample.entity.Post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

public class ControllerProfile {
    @FXML
    private Label profileUsername;
    @FXML
    private Label profileType;
    @FXML
    private Label userPosted;
    private final PostsRepository postsRepository = new PostsRepository();
    private Account userAccount;
    public void displayAllPostsByUser(Account account) throws SQLException {
        Vector<Post> posts = postsRepository.getAllPostByAccount(account);
        StringBuilder posting = new StringBuilder();
        for (int i = 0; i < posts.size(); i++) {
            posting.append("Title: ").append(posts.elementAt(i).getTitle()).append("\n\t").append("Post: ").append(posts.elementAt(i).getPosts()).append("\n");
        }
        userPosted.setText(posting.toString());
    }

    public void profileUsername(Account userAccount, Account account) throws SQLException {
        this.userAccount=userAccount;
        profileUsername.setPrefWidth(account.getUserName().length() * 24 + 2);
        profileUsername.setText(account.getUserName());
        profileType.setText(" " + account.getType());
        displayAllPostsByUser(account);

    }

    public void homeButtonPressed(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forum-home.fxml"));
        Parent root = loader.load();
        ControllerForumHome controllerForumHome = loader.getController();
        controllerForumHome.passAccount(userAccount);
        Scene forumHome = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(forumHome);
        window.show();
    }
}
