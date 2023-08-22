package lk.ijse.rentabike.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Salary;
import lk.ijse.rentabike.dto.tm.SalaryTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class SalaryModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Salary> getAll() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM Salaries";

        List<Salary> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return data;
    }

    public static boolean validateSalaryId(String salaryId) {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String query = "SELECT * FROM Salaries WHERE salaryId = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, salaryId);
            ResultSet rs = pstm.executeQuery();
            return !rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String generateSalaryId() {
        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newSalariesId = "";

        try {
            conn = getConnection(URL, props);

            // Query to get the last booking ID
            String query = "SELECT salaryId FROM Salaries ORDER BY salaryId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastSalaryId = "";
            if (rs.next()) {
                lastSalaryId = rs.getString("salaryId");
            }

            // Generate the new booking ID
            if (lastSalaryId.isEmpty()) {
                newSalariesId = "S001";
            } else {
                int lastId = Integer.parseInt(lastSalaryId.substring(2));
                newSalariesId = "S" + String.format("%03d", lastId + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database connection and resources
            try {
                rs.close();
            } catch (Exception e) { /* ignored */ }
            try {
                stmt.close();
            } catch (Exception e) { /* ignored */ }

        }

        return newSalariesId;
    }

}
