package lk.ijse.rentabike.model;

import lk.ijse.rentabike.db.DBConnection;

import java.sql.*;
import java.util.Properties;

public class LoginModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public boolean isUserValid(String username, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE user_name=? AND password=?";

        Connection con = DriverManager.getConnection(URL, props);
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, password);

        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Return true if a record is found, false otherwise
        }


    }
}
