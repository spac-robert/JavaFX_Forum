package sample.Repository;

import java.sql.*;

public class MySqlConn {

    public static Connection ConnectionDB() {
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/forumdbjavafx", "root",
                    "Spa306r*4834");
            return conn;

        } catch (Exception e) {
            System.out.println("There were errors while connecting to database");
            return null;
        }
    }
}
