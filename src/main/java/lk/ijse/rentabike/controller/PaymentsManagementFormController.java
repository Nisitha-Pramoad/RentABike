package lk.ijse.rentabike.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.rentabike.dto.Customer;
import lk.ijse.rentabike.dto.Payment;
import lk.ijse.rentabike.dto.tm.CustomerTM;
import lk.ijse.rentabike.dto.tm.PaymentTm;
import lk.ijse.rentabike.model.BookingModel;
import lk.ijse.rentabike.model.CustomerModel;
import lk.ijse.rentabike.model.PaymentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class PaymentsManagementFormController implements Initializable {
    public JFXButton dashboardbtn;
    public JFXButton bookingbtn;
    public JFXButton vehiclebtn;
    public JFXButton customerbtn;
    public JFXButton employeebtn;
    public JFXButton billingbtn;
    public JFXButton settingbtn;
    public JFXButton logoutbtn;

    public TableView<PaymentTm> tblPayment;
    public TableColumn colPayid;
    public TableColumn colPayamount;
    public TableColumn colPaydescription;
    public TableColumn colPaydate;
    public TableColumn colCid;
    public TableColumn colBid;
    public JFXButton reportbtn;
    public TableColumn colAction;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        tblPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("payId"));
        tblPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("payAmount"));
        tblPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("payDescription"));
        tblPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("payDate"));
        tblPayment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblPayment.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("bookingId"));
    }

    private void getAll() {
        try {
            ObservableList<PaymentTm> obList = FXCollections.observableArrayList();
            List<Payment> payList = PaymentModel.getAll();

            for (Payment payment : payList) {
                obList.add(new PaymentTm(
                        payment.getPayId(),
                        payment.getPayAmount(),
                        payment.getPayDescription(),
                        payment.getPayDate(),
                        payment.getcId(),
                        payment.getbId()
                ));
            }
            tblPayment.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
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
}
