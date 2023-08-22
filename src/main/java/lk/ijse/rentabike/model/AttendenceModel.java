package lk.ijse.rentabike.model;


import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Attendence;
import lk.ijse.rentabike.dto.tm.AttendenceTm;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class AttendenceModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Attendence> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Attendance";

        List<Attendence> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Attendence(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return data;
    }

    public static boolean validateAttendenceId(String customerId) {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String query = "SELECT * FROM Booking WHERE bookingId = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, customerId);
            ResultSet rs = pstm.executeQuery();
            return !rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String generateAttendenceId() {
        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newAttedenceId = "";

        try {
            conn = getConnection(URL, props);

            // Query to get the last booking ID
            String query = "SELECT attendenceId FROM Attendance ORDER BY attendenceId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastAttendenceId = "";
            if (rs.next()) {
                lastAttendenceId = rs.getString("attendenceId");
            }

            // Generate the new booking ID
            if (lastAttendenceId.isEmpty()) {
                newAttedenceId = "A001";
            } else {
                int lastId = Integer.parseInt(lastAttendenceId.substring(2));
                newAttedenceId = "A" + String.format("%03d", lastId + 1);
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

        return newAttedenceId;
    }
}
