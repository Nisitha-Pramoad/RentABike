package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {


    public TextField txtUsername;
    public PasswordField txtPassword;
    public Label lblForgotPassword;
    public Button SignInbtn;

    private int loginAttempts = 0;

    public void btnSignInToDashOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            LoginModel loginModel = new LoginModel();
            boolean isUserValid = loginModel.isUserValid(username, password);

            if (isUserValid) {
                // Open DashboardForm.fxml
                openDashboardForm(actionEvent);
            } else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    disableLoginButton();
                    new Alert(Alert.AlertType.WARNING, "System Locked. You have entered the wrong password multiple times. Please click on Forgot Password to unlock the system").show();
                } else {
                    showAlert("Invalid Credentials", "Wrong Username and Password!");
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void openDashboardForm(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }

    private void disableLoginButton() {
        txtUsername.setDisable(true);
        txtPassword.setDisable(true);
        SignInbtn.setDisable(true);
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    public void lblForgotPasswordOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        otpVerifyForm(mouseEvent);
    }

    private void otpVerifyForm(MouseEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/otpVerifyFrom.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Otp Verify");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }
}
