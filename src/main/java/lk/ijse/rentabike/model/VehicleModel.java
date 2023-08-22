package lk.ijse.rentabike.model;

import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Vehicle;
import lk.ijse.rentabike.dto.VehicleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class VehicleModel {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static List<Vehicle> getAll() throws SQLException {
        Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT * FROM Vehicle";

        List<Vehicle> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return data;
    }

    public static String generateVehicleId() {
        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newVehicleId = "";

        try {
            conn = getConnection(URL, props);

            // Query to get the last booking ID
            String query = "SELECT vehicleId FROM Vehicle ORDER BY vehicleId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastVehicleId = "";

            if (rs.next()) {
                lastVehicleId = rs.getString("vehicleId");
            }

            // Generate the new booking ID
            if (lastVehicleId.isEmpty()) {
                newVehicleId = "v001";
            } else {
                int lastId = Integer.parseInt(lastVehicleId.substring(2));
                newVehicleId = "v" + String.format("%03d", lastId + 1);
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

        return newVehicleId;
    }

    public static List<Vehicle> getAvailableVehicles() throws SQLException, ClassNotFoundException  {
        Connection con = DriverManager.getConnection(URL, props);
        String query = "SELECT * FROM Vehicle WHERE available = 'available'";

        List<Vehicle> availableVehicle = new ArrayList<>();


        // Find all rooms that are currently available
        ResultSet rs = con.createStatement().executeQuery(query);
        //rs = stmt.executeQuery(query);

        // Create Room objects for each available room and add them to the list
        while (rs.next()) {
            String vehicleid = rs.getString("vehicleId");
            String vehiclename = rs.getString("name");
            String vehicetype = rs.getString("type");
            double rentprice = rs.getDouble("rent_price");
            String mileage = rs.getString("mileage");
            String firstaidkit = rs.getString("first_aid_kit");
            String transmission = rs.getString("transmission");
            String roadsideassistance = rs.getString("roadside_assistance");
            String available = rs.getString("available");
            Vehicle vehicle = new Vehicle(vehicleid, vehiclename, vehicetype, rentprice, mileage, firstaidkit, transmission, roadsideassistance, available);
            availableVehicle.add(vehicle);
        }
        return availableVehicle;
    }

    public static List<VehicleStatus> getAvailableBikes() throws SQLException, ClassNotFoundException  {
        Connection con = DriverManager.getConnection(URL, props);
        String query = "SELECT * FROM VehicleStatus WHERE type='Bikes' AND available='available'";

        List<VehicleStatus> availableBikes = new ArrayList<>();


        // Find all rooms that are currently available
        ResultSet rs = con.createStatement().executeQuery(query);
        //rs = stmt.executeQuery(query);

        // Create Room objects for each available room and add them to the list
        while (rs.next()) {
            String vehicleid = rs.getString("vehicleId");
            String vehiclename = rs.getString("name");
            String vehicetype = rs.getString("type");
            double rentprice = rs.getDouble("rent_price");
            String customerid = rs.getString("customer_id");
            String available = rs.getString("available");
            VehicleStatus vehiclestatus = new VehicleStatus(vehicleid, vehiclename, vehicetype, rentprice, customerid , available);
            availableBikes.add(vehiclestatus);
        }
        return availableBikes;
    }

    public static int countBikes() {
        int count = 0;
        try {
            Connection conn = DriverManager.getConnection(URL, props);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM VehicleStatus WHERE type='Bikes'");
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int countAvailableScooters() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM VehicleStatus WHERE type = 'Scooter' AND available = 'available'";
        try (Connection conn = DriverManager.getConnection(URL, props);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public static int countScooters() {
        int count = 0;

        try (Connection conn = DriverManager.getConnection(URL, props);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM VehicleStatus WHERE type = 'Scooter'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    public static int countAvailableBicycles() {
        int availableBicycles = 0;
        try (Connection conn = DriverManager.getConnection(URL, props);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM VehicleStatus WHERE type='Bicycle' AND available='available'")) {
            if (rs.next()) {
                availableBicycles = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBicycles;
    }


    public static int countBicycles() {
        int count = 0;
        try {
            Connection conn = DriverManager.getConnection(URL, props);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM VehicleStatus WHERE type='Bicycle'");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int countAvailableCars() {
        int count = 0;

        // Open a connection to the database
        try (Connection conn = DriverManager.getConnection(URL, props)) {
            // Create a statement object
            Statement stmt = conn.createStatement();

            // Execute the SQL query and get the results
            String sql = "SELECT COUNT(*) FROM VehicleStatus WHERE type = 'Car' AND available = 'available'";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract the count from the result set
            if (rs.next()) {
                count = rs.getInt(1);
            }

            // Close the result set and statement
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int countThreeWheels() {
        try {
            Connection conn = DriverManager.getConnection(URL, props);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM VehicleStatus WHERE type='Three Wheel'");
            rs.next();
            int count = rs.getInt(1);

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw a custom exception
        }
    }

    public static int countAvailableThreeWheels() {
        int count = 0;
        try {
            Connection conn = DriverManager.getConnection(URL, props);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM VehicleStatus WHERE type='Three Wheel' AND available='available'");
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static List<String> availableVehicles() throws SQLException, ClassNotFoundException {


        Connection con = DriverManager.getConnection(URL, props);


        String query = "SELECT name FROM VehicleStatus WHERE available = 'available'";
        List<String> availableVehicles = new ArrayList<>();
        ResultSet rs = con.createStatement().executeQuery(query);
        while (rs.next()) {
            availableVehicles.add(rs.getString("name"));
        }
        return availableVehicles;
    }

    public static void expireBookings(Date currentDate) {
        try {
            Connection con = DriverManager.getConnection(URL, props);

            // Find all bookings where the check-out date is before the current date
            String query = "SELECT * FROM Booking WHERE dropOffDate < ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new Date(currentDate.getTime()));
            ResultSet rs = ps.executeQuery();

            // Update the corresponding room status to "available" and the booking status to "expired"
            while (rs.next()) {
                String choosebike = rs.getString("chooseBike");
                String updateQuery = "UPDATE VehicleStatus SET available = 'available' WHERE name = ?";
                PreparedStatement updatePs = con.prepareStatement(updateQuery);
                updatePs.setString(1, choosebike);
                updatePs.executeUpdate();
                String bookingId = rs.getString("bookingId");
                String updateBookingQuery = "UPDATE Booking SET bookingStatus = 'expired' WHERE bookingId = ?";
                PreparedStatement updateBookingPs = con.prepareStatement(updateBookingQuery);
                updateBookingPs.setString(1, bookingId);
                updateBookingPs.executeUpdate();
                updateBookingPs.close(); // Close the PreparedStatement
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
