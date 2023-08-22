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
import lk.ijse.rentabike.dto.Booking;
import lk.ijse.rentabike.dto.Customer;
import lk.ijse.rentabike.dto.tm.BookingTm;
import lk.ijse.rentabike.dto.tm.CustomerTM;
import lk.ijse.rentabike.model.BookingModel;
import lk.ijse.rentabike.model.CustomerModel;
import lk.ijse.rentabike.model.VehicleStatusModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class BookingManagementFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/rentabike";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public TableView<BookingTm> tblBooking;
    public TableColumn colBookingId;
    public TableColumn colBikeType;
    public TableColumn colPickuplocation;
    public TableColumn colPickupdate;
    public TableColumn colPickuptime;
    public TableColumn colDropofflocation;
    public TableColumn colDropoffdate;
    public TableColumn colDropofftime;
    public TableColumn colBookingStatus;
    public TableColumn colActon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        tblBooking.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        tblBooking.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("chooseBike"));
        tblBooking.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pickUpLocation"));
        tblBooking.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        tblBooking.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        tblBooking.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dropOffLocation"));
        tblBooking.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("dropOffDate"));
        tblBooking.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("dropOffTime"));
        tblBooking.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));

        tblBooking.getColumns().get(9).setCellValueFactory(param -> {
            ImageView cancel = new ImageView("img/table icons/cancel bike.png");
            HBox hbox = new HBox(20, cancel);
            hbox.setAlignment(Pos.CENTER);

            cancel.setOnMouseClicked(event -> {
                // Handle booked button click
                BookingModel.bookingCancel(param.getValue().getBookingId());

                getAll();
            });

            return new ReadOnlyObjectWrapper(hbox);
        });
    }

    private void getAll() {
        try {
            ObservableList<BookingTm> obList = FXCollections.observableArrayList();
            List<Booking> bookList = BookingModel.getAll();

            for (Booking booking : bookList) {
                obList.add(new BookingTm(
                        booking.getBookingId(),
                        booking.getChooseBike(),
                        booking.getPickUpLocation(),
                        booking.getPickUpDate(),
                        booking.getPickUpTime(),
                        booking.getDropOffLocation(),
                        booking.getDropOffDate(),
                        booking.getDropOffTime(),
                        booking.getBookingStatus()
                ));
            }
            tblBooking.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
}