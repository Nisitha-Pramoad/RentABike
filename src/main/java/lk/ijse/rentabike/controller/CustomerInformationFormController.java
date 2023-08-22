package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.rentabike.dto.Customer;
import lk.ijse.rentabike.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.rentabike.model.CustomerModel.generateCustomerId;

public class CustomerInformationFormController implements Initializable {
    public TextField txtCustomerId;
    public TextField txtFullname;
    public TextField txtAge;
    public TextField txtPhoneNumber;
    public TextField txtEmail;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtCountry;
    public TextField txtZipcode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCustomerId.setText(generateCustomerId());
    }


    public void BookingIdSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String CustomerId = generateCustomerId();
        String Fullname = txtFullname.getText();
        int Age = Integer.parseInt(txtAge.getText());
        String PhoneNumber = txtPhoneNumber.getText();
        String Email = txtEmail.getText();
        String Address = txtAddress.getText();
        String City = txtCity.getText();
        String Country = txtCountry.getText();
        String Zipcode = txtZipcode.getText();


        if (customerNameValidation(Fullname)) {
            if (customerAgeValidation(Age)) {
                if (customerEmailValidation(Email)) {


                    Customer custo = new Customer(CustomerId, Fullname, Age, PhoneNumber, Email, Address, City, Country, Zipcode);

                    try {

                        boolean isSaved = CustomerModel.save(custo);
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                    }

                } else {
                    new Alert(Alert.AlertType.WARNING,"Invalid email. Must contain an @ symbol.").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING,"Invalid age. Must be a number between 0 and 999.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING,"Invalid full name. Only letters and spaces allowed.").show();
        }



    }

    public boolean customerIdFormatValidation(String customerId) {
        if (!customerId.matches("^c\\d{3,4}$")) {
            return false;
        }
        return true;
    }



    public boolean customerNameValidation(String fullName) {
        if (!fullName.matches("^[a-zA-Z ]+$")) {
            return false;
        }
        return true;
    }



    public boolean customerAgeValidation(int age) {
        if (age < 20 || age > 99) {
            new Alert(Alert.AlertType.CONFIRMATION,"Invalid age. Must be a number between 20 and 99.").show();
            return false;
        }
        return true;
    }




    public boolean customerEmailValidation(String email) {
        if (!email.matches("^\\S+@\\S+$")) {

            return false;
        }
        return true;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String CustomerId = txtCustomerId.getText();
        try {
            boolean isDeleted = CustomerModel.delete(CustomerId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    public void CustomerIdSearchOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = CustomerModel.search(txtCustomerId.getText());
            if (customer != null) {
                txtCustomerId.setText(customer.getCustomerId());
                txtFullname.setText(customer.getFullName());
                txtAge.setText(String.valueOf(customer.getAge()));
                txtPhoneNumber.setText(String.valueOf(customer.getPhoneNumber()));
                txtEmail.setText(customer.getEmail());
                txtAddress.setText(customer.getAddress());
                txtCity.setText(customer.getCity());
                txtCountry.setText(customer.getCountry());
                txtZipcode.setText(customer.getZipCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String CustomerId = txtCustomerId.getText();
        String Fullname = txtFullname.getText();
        int Age = Integer.parseInt(txtAge.getText());
        String PhoneNumber = txtPhoneNumber.getText();
        String Email = txtEmail.getText();
        String Address = txtAddress.getText();
        String City = txtCity.getText();
        String Country = txtCountry.getText();
        String Zipcode = txtZipcode.getText();



        if (customerNameValidation(Fullname)) {
            if (customerAgeValidation(Age)) {
                if (customerEmailValidation(Email)) {

                    try {
                        boolean isUpdated = CustomerModel.update(CustomerId, Fullname, Age, PhoneNumber, Email, Address, City, Country, Zipcode);
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                    }


                } else {
                    new Alert(Alert.AlertType.WARNING,"Invalid email. Must contain an @ symbol.").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING,"Invalid age. Must be a number between 0 and 999.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING,"Invalid full name. Only letters and spaces allowed.").show();
        }
    }



}
