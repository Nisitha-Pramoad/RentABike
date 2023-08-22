package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class OtpVerifyFromController implements Initializable {
    public TextField txtOtpNumber;
    private String otp;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendOTP();
    }


    private void sendOTP() {
        // Code to send OTP via email
        String recipientEmail = "info.rentabikesrilanka@gmail.com";
        String subject = "OTP for System Unlock";
        otp = generateOTP();
        String messageContent = "Your OTP is: " + otp;


        try {
            EmailService.sendEmail(recipientEmail, subject, messageContent);
            //showAlert("OTP Sent", "An OTP has been sent to your email. Please check your inbox.");

        } catch (Exception e) {
            showAlert("Error", "Failed to send OTP. Please try again later.");
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otpNumber = random.nextInt(900000) + 100000; // Generate a random 6-digit number
        return String.valueOf(otpNumber);
    }

    public void OtpVerificationOnAction(ActionEvent actionEvent) throws IOException {
        String enteredOTP = txtOtpNumber.getText();
        // Replace with the correct OTP generated and sent via email

        if (otp.equals(enteredOTP)) {
            // Unlock the system or perform any desired action
            //showAlert("OTP Correct", "The entered OTP is correct. The system is now unlocked.");
            openDashboardForm(actionEvent);
        } else {
            // Display error message for incorrect OTP
            showAlert("OTP Incorrect", "The entered OTP is incorrect. Please try again.");
        }
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    private void openDashboardForm(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }
}
