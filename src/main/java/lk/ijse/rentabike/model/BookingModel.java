package lk.ijse.rentabike.model;

import javafx.scene.control.Alert;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Booking;
import lk.ijse.rentabike.dto.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class BookingModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Booking> getAll() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM Booking";

        List<Booking> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Booking(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return data;
    }

    public static String generateBookingId() {
        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newBookingId = "";

        try {
            conn = getConnection(URL, props);

            // Query to get the last booking ID
            String query = "SELECT bookingId FROM Booking ORDER BY bookingId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastBookingId = "";

            if (rs.next()) {
                lastBookingId = rs.getString("bookingId");
            }

            // Generate the new booking ID
            if (lastBookingId.isEmpty()) {
                newBookingId = "b001";
            } else {
                int lastId = Integer.parseInt(lastBookingId.substring(2));
                newBookingId = "b" + String.format("%03d", lastId + 1);
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

        return newBookingId;
    }


    public static void bookingCancel(String bookingId) {
        try {
            Connection con = DriverManager.getConnection(URL, props);

            // Update Booking table
            String updateBookingSql = "UPDATE Booking SET BookingStatus = 'cancel' WHERE bookingId = ?";
            PreparedStatement updateBookingStmt = con.prepareStatement(updateBookingSql);
            updateBookingStmt.setString(1, bookingId);
            updateBookingStmt.executeUpdate();

            // Update vehicleStatus table
            String updateVehicleStatusSql = "UPDATE VehicleStatus SET available = 'available' WHERE booking_id = ?";
            PreparedStatement updateVehicleStatusStmt = con.prepareStatement(updateVehicleStatusSql);
            updateVehicleStatusStmt.setString(1, bookingId);
            updateVehicleStatusStmt.executeUpdate();

            // Update vehicleStatus table
            String updatePaymentSql = "UPDATE Payment SET paymentDescription = 'Cancel Payment.' WHERE bId = ?";
            PreparedStatement updatePaymentStmt = con.prepareStatement(updatePaymentSql);
            updatePaymentStmt.setString(1, bookingId);
            updatePaymentStmt.executeUpdate();

            int rowsAffected = updateVehicleStatusStmt.executeUpdate();
            if (rowsAffected > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Booking has successfully canceled!").show();
                System.out.println("Booking has successfully canceled!");
            } else {
                new Alert(Alert.AlertType.WARNING, "Booking has failed to canceled!").show();
                System.out.println("Booking has failed to canceled!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean save(Booking booking) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234")) {
            String sql = "INSERT INTO Booking(bookingId, chooseBike, PickUpLocation, pickUpDate, pickUpTime, dropOffLocation, dropOffDate, dropOffTime, bookingStatus) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement pstm = con.prepareStatement(sql);
            //String bookingId = null;
            pstm.setString(1, booking.getBookingId());
            pstm.setString(2, booking.getChooseBike());
            pstm.setString(3, booking.getPickUpLocation());
            pstm.setDate(4, booking.getPickUpDate());
            pstm.setString(5, booking.getPickUpTime());
            pstm.setString(6, booking.getDropOffLocation());
            pstm.setDate(7, booking.getDropOffDate());
            pstm.setString(8, booking.getDropOffTime());
            pstm.setString(9, "booked");

            return pstm.executeUpdate() > 0;
        }
    }
}
