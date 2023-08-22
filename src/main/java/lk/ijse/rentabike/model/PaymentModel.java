package lk.ijse.rentabike.model;

import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Employee;
import lk.ijse.rentabike.dto.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PaymentModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Payment> getAll() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM Payment";

        List<Payment> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = con.createStatement().executeQuery("SELECT id FROM Payment");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Payment searchById(String id) throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Payment WHERE id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Payment(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }
}
