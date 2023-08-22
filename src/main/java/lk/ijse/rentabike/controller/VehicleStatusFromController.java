package lk.ijse.rentabike.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lk.ijse.rentabike.dto.VehicleStatus;
import lk.ijse.rentabike.dto.tm.VehicleStatusTm;
import lk.ijse.rentabike.model.VehicleStatusModel;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleStatusFromController  implements Initializable{
    public TableView<VehicleStatusTm> tblVehicleStatus;
    public TableColumn colVehicleId;
    public TableColumn colVehicleName;
    public TableColumn colType;
    public TableColumn colRentPrice;
    public TableColumn colCustomerId;
    public TableColumn colavailable;
    public TableColumn colAction;
    public TableColumn colbookingid;

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        tblVehicleStatus.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        tblVehicleStatus.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblVehicleStatus.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblVehicleStatus.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        tblVehicleStatus.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblVehicleStatus.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("bookingdId"));
        tblVehicleStatus.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("available"));

        tblVehicleStatus.getColumns().get(7).setCellValueFactory(param -> {
            ImageView booked = new ImageView("img/table icons/return bike.png");
            HBox hbox = new HBox(20, booked);
            hbox.setAlignment(Pos.CENTER);

            booked.setOnMouseClicked(event -> {
                // Handle booked button click
                VehicleStatusModel.vehicleReturn(param.getValue().getBookingdId());

                getAll();
            });

            return new ReadOnlyObjectWrapper(hbox);
        });
    }

    private void getAll() {
        try {
            ObservableList<VehicleStatusTm> obList = FXCollections.observableArrayList();
            List<VehicleStatus> vehiclestatusList = VehicleStatusModel.getAll();

            for (VehicleStatus vehiclestatuses : vehiclestatusList) {
                obList.add(new VehicleStatusTm(
                        vehiclestatuses.getVehicleId(),
                        vehiclestatuses.getName(),
                        vehiclestatuses.getType(),
                        vehiclestatuses.getRentPrice(),
                        vehiclestatuses.getCustomerId(),
                        vehiclestatuses.getBookingdId(),
                        vehiclestatuses.getAvailable()
                ));
            }
            tblVehicleStatus.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
