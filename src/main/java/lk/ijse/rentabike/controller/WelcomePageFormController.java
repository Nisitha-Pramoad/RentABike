package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class WelcomePageFormController implements Initializable {
    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {


        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Load the LoginPageForm and create a new stage
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();

        // Close the current stage
        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        expireBooings();
    }

    public static void expireBooings(){

        // Get the current date and time
        Date currentDate = new Date(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());

        // Expire any bookings that have check-out dates before the current date
        VehicleModel.expireBookings(currentDate);
    }
}
