package lk.ijse.rentabike.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.dto.VehicleStatus;
import lk.ijse.rentabike.model.AttendanceSystem;
import lk.ijse.rentabike.model.ProfitAnalizingModel;
import lk.ijse.rentabike.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



public class DashboardFormcontroller implements Initializable {
    public JFXButton dashboardbtn;
    public JFXButton bookingbtn;
    public JFXButton vehiclebtn;
    public JFXButton customerbtn;
    public JFXButton employeebtn;
    public JFXButton billingbtn;
    public JFXButton settingbtn;
    public JFXButton logoutbtn;

    public Label lblDayProfit;
    public Label lblWeeklyProfit;
    public Label lblMonthlyProfits;
    public Label lblYearlyProfits;
    public JFXButton reportbtn;

    public Label lblPresent;
    public Label lblTotalEmployee;

    public Label lblAvailableBikes;
    public Label lblTotalBikes;

    public Label lblAvailableScooters;
    public Label lblAllScootersCount;

    public Label lblAvailableBicycle;
    public Label lblAllBicycleCount;

    public Label lblAvailableCars;
    public Label lblAllCarsCount;

    public Label lblAvailableThreeWheels;
    public Label lblAllThreewheelCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profitAnalysis();
        availabilityStatusStatics();
    }


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

    public void profitAnalysis(){

        Map<String, Double> profits = ProfitAnalizingModel.profitanalysis();
        double todayProfit = profits.get("Today");
        double thisWeekProfit = profits.get("This week");
        double thisMonthProfit = profits.get("This month");
        double thisYearProfit = profits.get("This year");

        lblDayProfit.setText(Double.toString(todayProfit));
        lblWeeklyProfit.setText(Double.toString(thisWeekProfit));
        lblMonthlyProfits.setText(Double.toString(thisMonthProfit));
        lblYearlyProfits.setText(Double.toString(thisYearProfit));

    }

    private void availabilityStatusStatics() {
        int[] attendanceData = AttendanceSystem.main(null);
        int presentEmployees = attendanceData[0];
        int totalEmployees = attendanceData[1];

        lblPresent.setText(String.valueOf(presentEmployees));
        lblTotalEmployee.setText(String.valueOf(totalEmployees));

        try {
            List<VehicleStatus> vehicleSta = VehicleModel.getAvailableBikes();
            int BikesCount = VehicleModel.countBikes();

            lblAvailableBikes.setText(String.valueOf(vehicleSta.size()));
            lblTotalBikes.setText(String.valueOf(BikesCount));

            int availableScooters = VehicleModel.countAvailableScooters();
            int countScooters = VehicleModel.countScooters();

            lblAvailableScooters.setText(String.valueOf(availableScooters));
            lblAllScootersCount.setText(String.valueOf(countScooters));

            int availableBicycles = VehicleModel.countAvailableBicycles();
            int countALlBicycles = VehicleModel.countBicycles();

            lblAvailableBicycle.setText(String.valueOf(availableBicycles));
            lblAllBicycleCount.setText(String.valueOf(countALlBicycles));

            int availableCars = VehicleModel.countAvailableCars();
            int countAllCars = VehicleModel.countAvailableCars();

            lblAvailableCars.setText(String.valueOf(availableCars));
            lblAllCarsCount.setText(String.valueOf(countAllCars));

            int availableThreeWheel = VehicleModel.countAvailableThreeWheels();
            int countAllThreeWheels = VehicleModel.countThreeWheels();

            lblAvailableThreeWheels.setText(String.valueOf(availableThreeWheel));
            lblAllThreewheelCount.setText(String.valueOf(countAllThreeWheels));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
