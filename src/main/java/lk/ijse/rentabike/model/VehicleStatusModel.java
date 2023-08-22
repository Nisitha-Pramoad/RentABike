package lk.ijse.rentabike.model;

import javafx.scene.control.Alert;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.VehicleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VehicleStatusModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<VehicleStatus> getAll() throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM VehicleStatus";

        List<VehicleStatus> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new VehicleStatus(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return data;
    }


    public static void vehicleReturn(String bookingId) {
        try {
            Connection con = DriverManager.getConnection(URL, props);
            String sql = "UPDATE VehicleStatus SET available = 'available' WHERE booking_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, bookingId);

            // Update Reservation table
            String updateReservationSql = "UPDATE Booking SET bookingStatus = 'expired' WHERE bookingId = ?";
            PreparedStatement updateBookingStatusStmt = con.prepareStatement(updateReservationSql);
            updateBookingStatusStmt.setString(1, bookingId);
            updateBookingStatusStmt.executeUpdate();


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle return has successfully noted!").show();
                System.out.println("Vehicle return has successfully noted! ");
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to return vehicle").show();
                System.out.println("Failed to return vehicle ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
