package lk.ijse.rentabike.model;

import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Customer;
import lk.ijse.rentabike.dto.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class EmployeeModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Employee> getAll() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM Employee";

        List<Employee> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        ResultSet resultSet = con.createStatement().executeQuery("SELECT id FROM Employee");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Employee searchById(String id) throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Employee WHERE id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

    public static String generateEmployeeId() {
        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newEmployeeId = "";

        try {
            conn = getConnection(URL, props);

            // Query to get the last booking ID
            String query = "SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastEmployeeId = "";
            if (rs.next()) {
                lastEmployeeId = rs.getString("employeeId");
            }

            // Generate the new booking ID
            if (lastEmployeeId.isEmpty()) {
                newEmployeeId = "E001";
            } else {
                int lastId = Integer.parseInt(lastEmployeeId.substring(2));
                newEmployeeId = "E" + String.format("%03d", lastId + 1);
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

        return newEmployeeId;
    }

}
