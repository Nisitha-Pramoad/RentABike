package lk.ijse.rentabike.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.Booking;
import lk.ijse.rentabike.dto.Customer;
import lk.ijse.rentabike.model.BookingModel;
import lk.ijse.rentabike.model.CustomerModel;
import lk.ijse.rentabike.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;
import static lk.ijse.rentabike.model.BookingModel.generateBookingId;
import static lk.ijse.rentabike.model.CustomerModel.generateCustomerId;


public class BookingFillingController implements Initializable {
    public JFXButton dashboardbtn;
    public JFXButton bookingbtn;
    public JFXButton vehiclebtn;
    public JFXButton customerbtn;
    public JFXButton employeebtn;
    public JFXButton billingbtn;
    public JFXButton settingbtn;
    public JFXButton logoutbtn;
    public Button BookingManagePagebtn;

    public JFXComboBox cmbChooseBike;
    public JFXComboBox cmbPickUpLocation;
    public JFXComboBox cmbPickUpTime;
    public JFXComboBox cmbDropOffLocation;
    public JFXComboBox cmbDropOffTime;
    public DatePicker dtpkPickUpLocation;
    public DatePicker dtpkDropOffLocation;
    public TextField txtBookingId;

    public TextField txtCustomerId;
    public TextField txtFullName;
    public TextField txtAge;
    public TextField txtPhoneNumber;
    public TextField txtEmail;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtCountry;
    public TextField txtZipCode;
    public JFXButton reportbtn;


    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Stage window = (Stage)dashboardbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Dashboard");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnBookingOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/BookingPart1Filling.fxml"));
        Stage window = (Stage)bookingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Booking");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/VehiclePreviewForm.fxml"));
        Stage window = (Stage)vehiclebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Vehicle Preview");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/CustomerManagementForm.fxml"));
        Stage window = (Stage)customerbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Customer Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnEmployeeManagementOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/EmployeeManagementForm.fxml"));
        Stage window = (Stage)employeebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Employee Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnBillingOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/PaymentsManagementForm.fxml"));
        Stage window = (Stage)billingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Payments Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/reportManageForm.fxml"));
        Stage window = (Stage)reportbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Reports");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnSettingOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/SettingForm.fxml"));
        Stage window = (Stage)settingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Setting");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LogOutPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Logout page");
        stage.centerOnScreen();
        stage.show();
        currentStage.close();

    }

    public void btnBookingManagePageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/BookingManagementForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Booking Management");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
    }

    private void setUi(String form, JFXButton btn) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/"+form+".fxml"));
        Stage window = (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle(form);
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> locationList = FXCollections.observableArrayList(
                "Negombo",
                "Mirissa",
                "Weligama",
                "Ella",
                "Hikkaduwa",
                "Galle",
                "Unawatuna",
                "Kandy",
                "Arugam Bay",
                "Sigiriya",
                "Kalpitiya",
                "Trincomalee",
                "Tangalle",
                "Dickwella",
                "Anuradhapura",
                "Kataragama",
                "Matara",
                "Jaffna",
                "Batticola",
                "Benthota",
                "Nuwara Eliya",
                "Polonnaruwa"
        );
        cmbPickUpLocation.setItems(locationList);
        cmbDropOffLocation.setItems(locationList);

        ObservableList<String> timeList = FXCollections.observableArrayList();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j += 15) {
                String time = String.format("%02d:%02d", i, j);
                if (i < 12) {
                    time += " am";
                } else if (i == 12) {
                    time += " pm";
                } else {
                    time = String.format("%02d:%02d", i - 12, j) + " pm";
                }
                timeList.add(time);
            }
        }
        cmbPickUpTime.setItems(timeList);
        cmbDropOffTime.setItems(timeList);

        updateAvailableVehicles();
    }


    public boolean btnBooknowOnAction(ActionEvent actionEvent) throws Exception {
        String customerId = generateCustomerId();
        String fullName = txtFullName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String Stringage = txtAge.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String zipCode = txtZipCode.getText();

        String bookingId = generateBookingId();
        String chooseBike = cmbChooseBike.getValue().toString();
        String pickUpLocation = cmbPickUpLocation.getValue().toString();
        Date pickUpDate = Date.valueOf(dtpkPickUpLocation.getValue());
        String pickUpTime = cmbPickUpTime.getValue().toString();
        String dropOffLocation = cmbDropOffLocation.getValue().toString();
        Date dropOffDate = Date.valueOf(dtpkDropOffLocation.getValue());
        String dropOffTime = cmbDropOffTime.getValue().toString();

        String paymentId = generatePaymentId();
        double bikeRate = getBikeRateFromDatabase(chooseBike);
        LocalDate paymentDate = LocalDate.now();

        String ageText = txtAge.getText();
        if (ageText.isEmpty()) {
            // Handle the age validation error
            new Alert(Alert.AlertType.ERROR, "Age is required.").show();
            return false ;
        }

        try {
            age = Integer.parseInt(Stringage);
        } catch (NumberFormatException e) {
            // Handle the age validation error
            new Alert(Alert.AlertType.ERROR, "Invalid age. Age must be a number.").show();
            return false;
        }

        if (fullName.isEmpty() || phoneNumber.isEmpty() || Stringage.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() ||
                country.isEmpty() || zipCode.isEmpty() || chooseBike.isEmpty() || pickUpLocation.isEmpty() ||
                pickUpTime.isEmpty() || dropOffLocation.isEmpty() || dropOffTime.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return false;
        }else if (!fullName.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtFullName.requestFocus();
            return false;
        }else if(!phoneNumber.matches("^[+]?[0-9]{1,20}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid phone number").show();
            txtPhoneNumber.requestFocus();
            return false;
        }else if (age < 21 || age > 99) {
            new Alert(Alert.AlertType.WARNING, "Invalid age. Must be a number between 21 and 99.").show();
            txtAge.requestFocus();
            return false;
        }else if (!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid email. Must contain an @ symbol.").show();
            txtEmail.requestFocus();
            return false;
        }else if (!zipCode.matches("^[0-9]{1,10}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid zip code").show();
            txtZipCode.requestFocus();
            return false;
        }else if (!country.matches("^[a-zA-Z\\s]{1,15}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid country name characters").show();
            txtCountry.requestFocus();
            return false;
        }else if (!address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid address format.").show();
            txtAddress.requestFocus();
            return false;
        }


        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            Customer customer = new Customer(customerId, fullName, age, phoneNumber, email, address, city, country, zipCode);
            boolean customerAdded = CustomerModel.save(customer);

            if (!customerAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Booking booking = new Booking(bookingId, chooseBike, pickUpLocation, pickUpDate, pickUpTime, dropOffLocation, dropOffDate, dropOffTime, zipCode);
            boolean bookingAdded = BookingModel.save(booking);
                if (!bookingAdded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
            boolean bikeUpdated =  updateBikeStatusInDatabase(chooseBike, "booked", customerId, bookingId);
                if (!bikeUpdated) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

            boolean paymentSaved = savePaymentToDatabase(paymentId, bikeRate, "Payment Success!!" , paymentDate, customerId, bookingId);
            if (!paymentSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);

            if (bookingAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Booking has been saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Booking has not been saved successfully").show();
            }

            sendBookingConfirmationEmail(customerId, fullName, email, bookingId, chooseBike, pickUpLocation, pickUpDate, pickUpTime, dropOffLocation, dropOffDate, dropOffTime);
            updateAvailableVehicles();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return false;
    }

    // send customer details to email sender class
    public void sendBookingConfirmationEmail(String customerId, String customerName, String customerEmail,
                                             String bookingId, String chooseBike, String pickUpLocation,
                                             Date pickUpDate, String pickUpTime, String dropOffLocation,
                                             Date dropOffDate, String dropOffTime) {

        StringBuilder messageContent = new StringBuilder();
        messageContent.append("Dear ").append(customerName).append(",\n\n");
        messageContent.append("Thank you for booking a bike with us!\n\n");
        messageContent.append("Booking Details:\n");
        messageContent.append("Booking ID: ").append(bookingId).append("\n");
        messageContent.append("Bike: ").append(chooseBike).append("\n");
        messageContent.append("Pick-up Location: ").append(pickUpLocation).append("\n");
        messageContent.append("Pick-up Date: ").append(pickUpDate).append("\n");
        messageContent.append("Pick-up Time: ").append(pickUpTime).append("\n");
        messageContent.append("Drop-off Location: ").append(dropOffLocation).append("\n");
        messageContent.append("Drop-off Date: ").append(dropOffDate).append("\n");
        messageContent.append("Drop-off Time: ").append(dropOffTime).append("\n\n");
        messageContent.append("Please feel free to contact us if you have any further questions.\n\n");
        messageContent.append("Best regards,\nYour Rent A Bike Team");

        EmailService.sendEmail(customerEmail, "Booking Confirmation", messageContent.toString());
    }

    private boolean savePaymentToDatabase(String paymentId, double bikeRate, String s, LocalDate paymentDate, String customerId, String bookingId) throws Exception{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234");
            String query = "INSERT INTO Payment (paymentId, paymentAmount, paymentDescription, paymentDate, cId, bId) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, paymentId);
            pstmt.setDouble(2, bikeRate);
            pstmt.setString(3, s);
            pstmt.setString(4, String.valueOf(paymentDate));
            pstmt.setString(5, customerId);
            pstmt.setString(6, bookingId);

        return pstmt.executeUpdate() > 0;
    }

    private String generatePaymentId() {
        /*UUID uuid = UUID.randomUUID();
        String paymentId = "PAY-" + uuid.toString();
        return paymentId;*/

        // Establish database connection
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String newPaymentId = "";

        try {
            conn = getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234");

            // Query to get the last booking ID
            String query = "SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last booking ID
            String lastPaymentId = "";
            if (rs.next()) {
                lastPaymentId = rs.getString("paymentId");
            }

            // Generate the new booking ID
            if (lastPaymentId.isEmpty()) {
                newPaymentId = "p001";
            } else {
                int lastId = Integer.parseInt(lastPaymentId.substring(2));
                newPaymentId = "p" + String.format("%03d", lastId + 1);
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

        return newPaymentId;
    }

    private double getBikeRateFromDatabase(String selectVehicle) {
        // Initialize the rate to zero
        double rate = 0.0;

        // Connect to the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234")) {
            // Create a prepared statement to retrieve the room rate based on the room type
            String sql = "SELECT rent_price FROM VehicleStatus WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Set the parameter values
                stmt.setString(1, selectVehicle);

                // Execute the query and get the result set
                try (ResultSet rs = stmt.executeQuery()) {
                    // If a result is found, retrieve the rate
                    if (rs.next()) {
                        rate = rs.getDouble("rent_price");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rate;
    }

    private String getLastCustomerId() {
        String lastCustomerId = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234");

            // Query to get the last guest ID
            String query = "SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last guest ID
            if (rs.next()) {
                lastCustomerId = rs.getString("customerId");
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

        return lastCustomerId;
    }

    private String getLastBookingId() {
        String lastBookingId = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234");

            // Query to get the last guest ID
            String query = "SELECT bookingId FROM Booking ORDER BY bookingId DESC LIMIT 1";

            // Execute the query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the last guest ID
            if (rs.next()) {
                lastBookingId = rs.getString("bookingId");
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
        return lastBookingId;
    }


    private String updateAvailableVehicles() {
        String selectedRoom = null;
        try {
            List<String> availableVehicles = VehicleModel.availableVehicles();

            cmbChooseBike.getItems().clear();
            cmbChooseBike.getItems().addAll(availableVehicles);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return selectedRoom;
    }

    public boolean updateBikeStatusInDatabase(String chooseBike, String vehicleStatus, String cid, String bookingId) throws Exception{
        String vehicle_chooseBike = chooseBike;
        String vehicle_Status = vehicleStatus;
        String customer_id = cid;
        String booking_id = bookingId;

            Connection conn = getConnection("jdbc:mysql://localhost:3306/rentabike", "root", "1234");
            String query = "UPDATE VehicleStatus SET available = ?, customer_id = ?, booking_id = ? WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vehicle_Status);
            pstmt.setString(2, customer_id);
            pstmt.setString(3, booking_id);
            pstmt.setString(4, vehicle_chooseBike);

            return pstmt.executeUpdate() > 0;

    }


}

    
