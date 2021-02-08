package sample.Repository;

import sample.entity.Account;
import sample.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

public class PostsRepository implements GenericRepository<Post> {
    private final Connection conn;

    public PostsRepository() {
        conn = MySqlConn.ConnectionDB();
    }

    @Override
    public void insertData(Post data) throws SQLException {
        String query = "insert into posts values(?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, null);
        statement.setString(2, data.getId_user());
        statement.setString(3, data.getTitle());
        statement.setString(4, data.getPosts());

        statement.execute();
    }

    @Override
    public void deleteData(String id_post) throws SQLException {
        String query = "delete from posts where id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, id_post);

        statement.execute();
    }

    @Override
    public void updateData(String id) throws SQLException {

    }

    @Override
    public Vector<Post> getAll() throws SQLException {
        Vector<Post> allPosts = new Vector<>();
        String query = "select * from posts";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            allPosts.add(new Post(result.getString("id"), result.getString("id_user"),
                    result.getString("title"), result.getString("post")));
        }
        return allPosts;
    }

    public Vector<Post> getAllPostByAccount(Account account) throws SQLException {
        Vector<Post> posts = new Vector<>();
        String query = "select * from posts where id_user=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, account.getId());
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            posts.add(new Post(result.getString("id"), result.getString("id_user"),
                    result.getString("title"), result.getString("post")));
        }
        return posts;
    }

}
