package sample.Repository;

import sample.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

public class UsersRepository implements GenericRepository<Account> {
    private final Connection conn;

    public UsersRepository() {
        conn = MySqlConn.ConnectionDB();
    }

    @Override
    public void insertData(Account account) throws SQLException {
        String query = "insert into users values(?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, null);
        statement.setString(2, account.getUserName());
        statement.setString(3, account.getPassword());
        statement.setString(4, "user");

        statement.execute();
    }

    @Override
    public void deleteData(String id) throws SQLException {
        String query = "delete from users where id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, id);

        statement.execute();
    }

    @Override
    public void updateData(String id) throws SQLException {
        String query = "update users set type='admin' where name=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, id);

        statement.execute();
    }

    @Override
    public Vector<Account> getAll() throws SQLException {

        Vector<Account> allUsers = new Vector<>();
        String query = "select * from users";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            allUsers.add(new Account(result.getString("id"),result.getString("name"),result.getString("password"),result.getString("user")));
        }
        return allUsers;
    }

    public Optional<Account> findUserByName(String name) throws SQLException {

        String query = "select * from users where name=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next()) {

            String id = result.getString(1);
            String userName = result.getString(2);
            String password = result.getString(3);
            String type = result.getString(4);
            return Optional.of(new Account(id, userName, password, type));
        }
        return Optional.empty();
    }

    public Vector<Account> allNamesStartingWithAValue(String name) throws SQLException {
        Vector<Account> allNames = new Vector<>();
        Account account;
        String query = "select * from users where name like ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name + "%");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String id = result.getString(1);
            String userName = result.getString(2);
            String password = result.getString(3);
            String type = result.getString(4);
            account = new Account(id, userName, password, type);
            allNames.add(account);
        }
        return allNames;
    }
}
