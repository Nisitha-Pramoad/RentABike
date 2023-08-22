DROP DATABASE IF EXISTS RentABike;
CREATE DATABASE IF NOT EXISTS RentABike;
USE RentABike;

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer (
                                        customerId VARCHAR(15) NOT NULL,
                                        name VARCHAR(50),
                                        age INT,
                                        contact VARCHAR(20),
                                        email VARCHAR(50),
                                        address VARCHAR(100),
                                        city VARCHAR(50),
                                        country VARCHAR(50),
                                        zip_code VARCHAR(20),
                                        CONSTRAINT PRIMARY KEY (customerId)
);

DROP TABLE IF EXISTS Booking;
CREATE TABLE IF NOT EXISTS Booking (
                                       bookingId VARCHAR(15) NOT NULL,
                                       chooseBike VARCHAR(50),
                                       PickUpLocation VARCHAR(100),
                                       pickUpDate DATE,
                                       pickUpTime VARCHAR(10),
                                       dropOffLocation VARCHAR(100),
                                       dropOffDate DATE,
                                       dropOffTime VARCHAR(10),
                                       bookingStatus VARCHAR(10),
                                      /* cid VARCHAR(15),*/
                                       CONSTRAINT PRIMARY KEY (bookingId)
                                       /*CONSTRAINT FOREIGN KEY(cId) REFERENCES Customer(customerId) on Delete Cascade on Update Cascade*/
);



DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment (
                                       paymentId VARCHAR(15) NOT NULL,
                                       paymentAmount DOUBLE,
                                       paymentDescription VARCHAR(100),
                                       paymentDate VARCHAR(15),
                                       cId VARCHAR(15),
                                       bId VARCHAR(15),
                                       CONSTRAINT PRIMARY KEY (paymentId),
                                       CONSTRAINT FOREIGN KEY(cId) REFERENCES Customer(customerId) on Delete Cascade on Update Cascade,
                                       CONSTRAINT FOREIGN KEY(bId) REFERENCES Booking(bookingId) on Delete Cascade on Update Cascade
);

DROP TABLE IF EXISTS Vehicle;
CREATE TABLE IF NOT EXISTS Vehicle (
                                       vehicleId VARCHAR(15) NOT NULL,
                                       name VARCHAR(50),
                                       type VARCHAR(50),
                                       rent_price DOUBLE,
                                       mileage VARCHAR(50),
                                       first_aid_kit VARCHAR(50),
                                       transmission VARCHAR(50),
                                       roadside_assistance VARCHAR(50),
                                       available VARCHAR(50),
                                       CONSTRAINT PRIMARY KEY (vehicleId)
);


DROP TABLE IF EXISTS VehicleStatus;
CREATE TABLE IF NOT EXISTS VehicleStatus (
                                       vehicleId VARCHAR(15) NOT NULL,
                                       name VARCHAR(50),
                                       type VARCHAR(50),
                                       rent_price DOUBLE,
                                       customer_id VARCHAR(50),
                                       booking_id VARCHAR(50),
                                       available VARCHAR(50),
                                       CONSTRAINT PRIMARY KEY (vehicleId)
);



DROP TABLE IF EXISTS BookingDetails;
CREATE TABLE IF NOT EXISTS BookingDetails (
                                              vId VARCHAR(15),
                                              bId VARCHAR(15),
                                              CONSTRAINT PRIMARY KEY (vId, bId),
                                              CONSTRAINT FOREIGN KEY (vId) REFERENCES Vehicle(vehicleId) on Delete Cascade on Update Cascade,
                                              CONSTRAINT FOREIGN KEY (bId) REFERENCES Booking(bookingId) on Delete Cascade on Update Cascade
);


/*employee table*/

CREATE TABLE User (
                      user_name VARCHAR(50) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      CONSTRAINT PRIMARY KEY (user_name)
);

CREATE TABLE Employee (
                          employeeId VARCHAR(50),
                          employeeTyped VARCHAR(50),
                          name VARCHAR(50) NOT NULL,
                          nic VARCHAR(20) NOT NULL,
                          address VARCHAR(100),
                          contact INT,
                          email VARCHAR(50),
                          CONSTRAINT PRIMARY KEY (employeeId)
);

CREATE TABLE Salaries (
                          salaryId VARCHAR(50),
                          description VARCHAR(100),
                          amount DOUBLE,
                          type VARCHAR(50),
                          month VARCHAR(50),
                          employeeId VARCHAR(50),
                          CONSTRAINT PRIMARY KEY (salaryId)
);

CREATE TABLE Attendance (
                            attendenceId VARCHAR(50),
                            date DATE,
                            holiday VARCHAR(50),
                            signInTime VARCHAR(50),
                            signOutTime VARCHAR(50),
                            employeeId VARCHAR(50),
                            CONSTRAINT PRIMARY KEY (attendenceId)
                            /*CONSTRAINT FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) on Delete Cascade on Update Cascade*/
);


INSERT INTO Customer VALUES('c001', 'John Doe', 28 ,'+94754789723','Danapala@gmail.com','123 Main St', 'New York', 'USA' , '10001');
INSERT INTO Customer VALUES('c002', 'Jane Smith', 35, '+44789562341', 'jane.smith@example.com', '456 Elm St', 'London', 'UK', '8006');
INSERT INTO Customer VALUES('c003', 'Robert Johnson', 42, '+16175551212', 'robert.johnson@example.com', '789 Maple Ave', 'Chicago', 'USA', '60611');
INSERT INTO Customer VALUES('c004', 'Maria Garcia', 29, '+34917654321', 'maria.garcia@example.com', '10 Calle de Alcala', 'Madrid', 'Spain', '28001');
INSERT INTO Customer VALUES('c005', 'Jens Schmidt', 46, '+49123456789', 'jens.schmidt@example.com', '321 Rosenstrasse', 'Berlin', 'Germany', '10115');
INSERT INTO Customer VALUES('c006', 'Yuji Tanaka', 31, '+81345678901', 'yuji.tanaka@example.com', '456 Shibuya Crossing', 'Tokyo', 'Japan', '0002');
INSERT INTO Customer VALUES('c007', 'Sophie Dubois', 25, '+33123456789', 'sophie.dubois@example.com', '789 Rue de Rivoli', 'Paris', 'France', '75001');
INSERT INTO Customer VALUES('c008', 'Miguel Rodriguez', 36, '+5215555555555', 'miguel.rodriguez@example.com', '123 Av. Reforma', 'Mexico City', 'Mexico', '06600');
INSERT INTO Customer VALUES('c009', 'Anastasia Ivanova', 27, '+74951234567', 'anastasia.ivanova@example.com', '456 Nevsky Prospekt', 'St. Petersburg', 'Russia', '191023');
INSERT INTO Customer VALUES('c010', 'Kim Min-Jae', 33, '+8221234567', 'kim.minjae@example.com', '789 Gangnam-gu', 'Seoul', 'South Korea', '06001');
INSERT INTO Customer VALUES('c011', 'Lucas Silva', 30, '+5511999999999', 'lucas.silva@example.com', '123 Avenida Paulista', 'Sao Paulo', 'Brazil', '01310');



INSERT INTO Booking VALUES('b001', 'Mountain Bike', 'Nugegoda', '2023-04-01','11.23', 'Nugegoda', '2023-04-03','20.54 pm', 'expired');
INSERT INTO Booking VALUES('b002', 'City Bike', 'Colombo 07', '2023-04-06', '09.30', 'Colombo 03', '2023-04-07', '17.45 pm' , 'expired');
INSERT INTO Booking VALUES('b003', 'Mountain Bike', 'Kandy', '2023-04-08', '14.00', 'Kandy', '2023-04-10', '18.30 pm' , 'expired');
INSERT INTO Booking VALUES('b004', 'Electric Bike', 'Negombo', '2023-04-11', '10.15', 'Negombo', '2023-04-13', '19.00 pm' , 'expired');
INSERT INTO Booking VALUES('b005', 'City Bike', 'Galle', '2023-04-15', '11.45', 'Galle', '2023-04-16', '16.30 pm' , 'expired');
INSERT INTO Booking VALUES('b006', 'Mountain Bike', 'Nuwara Eliya', '2023-04-19', '08.00', 'Nuwara Eliya', '2023-04-21', '12.15 pm' , 'expired');
INSERT INTO Booking VALUES('b007', 'Electric Bike', 'Matara', '2023-04-23', '13.30', 'Matara', '2023-04-25', '16.45 pm' , 'expired');
INSERT INTO Booking VALUES('b008', 'City Bike', 'Anuradhapura', '2023-04-27', '10.00', 'Anuradhapura', '2023-04-28', '15.15 pm', 'expired');
INSERT INTO Booking VALUES('b009', 'Mountain Bike', 'Polonnaruwa', '2023-04-01', '12.45', 'Polonnaruwa', '2023-04-03', '19.30 pm', 'expired');
INSERT INTO Booking VALUES('b010', 'Electric Bike', 'Trincomalee', '2023-04-05', '09.00', 'Trincomalee', '2023-04-06', '13.45 pm', 'expired');

INSERT INTO Payment VALUES('p001', 4100.50, 'Payment Success!!', '2023-03-20', 'c001', 'b001');
INSERT INTO Payment VALUES('p002', 3375.00, 'Payment Success!!', '2023-03-21', 'c002', 'b002');
INSERT INTO Payment VALUES('p003', 5520.00, 'Payment Success!!', '2023-03-22', 'c003', 'b003');
INSERT INTO Payment VALUES('p004', 6550.00, 'Payment Success!!', '2023-03-22', 'c004', 'b004');
INSERT INTO Payment VALUES('p005', 4200.00, 'Payment Success!!', '2023-03-23', 'c005', 'b005');
INSERT INTO Payment VALUES('p006', 2150.00, 'Payment Success!!', '2023-03-23', 'c006', 'b006');
INSERT INTO Payment VALUES('p007', 2230.00, 'Payment Success!!', '2023-03-24', 'c007', 'b007');
INSERT INTO Payment VALUES('p008', 1250.00, 'Payment Success!!', '2023-03-24', 'c008', 'b008');
INSERT INTO Payment VALUES('p009', 2380.00, 'Payment Success!!', '2023-03-25', 'c009', 'b009');
INSERT INTO Payment VALUES('p010', 4100.00, 'Payment Success!!', '2023-03-25', 'c010', 'b010');
INSERT INTO Payment VALUES('p011', 3200.00, 'Payment Success!!', '2023-03-26', 'c011', 'b010');
INSERT INTO Payment VALUES('p012', 4700.00, 'Payment Success!!', '2023-03-27', 'C011', 'b010');
INSERT INTO Payment VALUES('p013', 1900.00, 'Payment Success!!', '2023-03-27', 'C011', 'b010');
INSERT INTO Payment VALUES('p014', 2600.00, 'Payment Success!!', '2023-03-28', 'C011', 'b010');
INSERT INTO Payment VALUES('p015', 5400.00, 'Payment Success!!', '2023-03-29', 'C011', 'b010');
INSERT INTO Payment VALUES('p016', 3100.00, 'Payment Success!!', '2023-03-30', 'C011', 'b010');
INSERT INTO Payment VALUES('p017', 1850.00, 'Payment Success!!', '2023-03-30', 'C011', 'b010');
INSERT INTO Payment VALUES('p018', 4250.00, 'Payment Success!!', '2023-04-01', 'C011', 'b010');
INSERT INTO Payment VALUES('p019', 4900.00, 'Payment Success!!', '2023-04-02', 'C011', 'b010');
INSERT INTO Payment VALUES('p020', 6300.00, 'Payment Success!!', '2023-04-03', 'C011', 'b010');
INSERT INTO Payment VALUES('p021', 3700.00, 'Payment Success!!', '2023-04-04', 'C011', 'b010');
INSERT INTO Payment VALUES('p022', 2800.00, 'Payment Success!!', '2023-04-05', 'C011', 'b010');
INSERT INTO Payment VALUES('p023', 2150.00, 'Payment Success!!', '2023-04-06', 'C011', 'b010');
INSERT INTO Payment VALUES('p024', 4150.00, 'Payment Success!!', '2023-04-07', 'C011', 'b010');
INSERT INTO Payment VALUES('p025', 4200.00, 'Payment Success!!', '2023-04-08', 'C011', 'b010');
INSERT INTO Payment VALUES('p026', 1800.00, 'Payment Success!!', '2023-04-09', 'C011', 'b010');
INSERT INTO Payment VALUES('p027', 1230.00, 'Payment Success!!', '2023-04-10', 'C011', 'b010');
INSERT INTO Payment VALUES('p028', 2980.00, 'Payment Success!!', '2023-04-11', 'C011', 'b010');
INSERT INTO Payment VALUES('p029', 3850.00, 'Payment Success!!', '2023-04-12', 'C011', 'b010');
INSERT INTO Payment VALUES('p030', 5100.00, 'Payment Success!!', '2023-04-12', 'C011', 'b010');
INSERT INTO Payment VALUES('p031', 3400.00, 'Payment Success!!', '2023-04-13', 'C011', 'b010');
INSERT INTO Payment VALUES('p032', 2400.00, 'Payment Success!!', '2023-04-13', 'C011', 'b010');
INSERT INTO Payment VALUES('p033', 1780.00, 'Payment Success!!', '2023-04-14', 'C011', 'b010');
INSERT INTO Payment VALUES('p034', 3650.00, 'Payment Success!!', '2023-04-15', 'C011', 'b010');
INSERT INTO Payment VALUES('p035', 4300.00, 'Payment Success!!', '2023-04-23', 'C011', 'b010');
INSERT INTO Payment VALUES('p036', 2100.00, 'Payment Success!!', '2023-04-24', 'C011', 'b010');
INSERT INTO Payment VALUES('p037', 1430.00, 'Payment Success!!', '2023-04-25', 'C011', 'b010');
INSERT INTO Payment VALUES('p038', 2750.00, 'Payment Success!!', '2023-04-25', 'C011', 'b010');
INSERT INTO Payment VALUES('p039', 4650.00, 'Payment Success!!', '2023-04-26', 'C011', 'b010');
INSERT INTO Payment VALUES('p040', 5700.00, 'Payment Success!!', '2023-04-27', 'C011', 'b010');



INSERT INTO Vehicle VALUES('v001', 'Hero XPluse 200 cc', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('v002', 'Honda X Blade', 'Bikes', 15000, 'unlimited', 'no', 'Manual', 'yes', 'booked');
INSERT INTO Vehicle VALUES('v003', 'Honda XR 250', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('v004', 'Honda Hornet 160 R', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'no', 'available');
INSERT INTO Vehicle VALUES('v005', 'Honda Dio 110 CC', 'Scooter', 1000, 'unlimited', 'yes', 'Automatic', 'yes', 'available');
INSERT INTO Vehicle VALUES('v006', 'Honda Navi 110 CC', 'Scooter', 1000, 'unlimited', 'no', 'Automatic', 'yes', 'booked');
INSERT INTO Vehicle VALUES('v007', 'Ladies Cycle', 'Bicycle', 800, 'unlimited', 'yes', 'Manual', 'yes', 'booked');
INSERT INTO Vehicle VALUES('v008', 'Mountain Cycle', 'Bicycle', 800, 'unlimited', 'yes', 'Manual', 'no', 'booked');
INSERT INTO Vehicle VALUES('v009', 'Bajaj Qute', 'Car', 4000, 'unlimited', 'no' , 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('v010', 'Tuk Tuk 300 CC', 'Three Wheel', 3500, 'unlimited', 'yes', 'Manual', 'yes', 'available');

INSERT INTO VehicleStatus VALUES('v001', 'Hero XPluse 200 cc', 'Bikes', 15000, 'C001', 'b001', 'available');
INSERT INTO VehicleStatus VALUES('v002', 'Honda X Blade', 'Bikes', 15000, 'C002' ,'b002', 'available');
INSERT INTO VehicleStatus VALUES('v003', 'Honda XR 250', 'Bikes', 15000, 'C003','b003', 'available');
INSERT INTO VehicleStatus VALUES('v004', 'Honda Hornet 160 R', 'Bikes', 15000, 'c004' ,'b004', 'available');
INSERT INTO VehicleStatus VALUES('v005', 'Honda Dio 110 CC', 'Scooter', 1000, 'c005' ,'b005', 'available');
INSERT INTO VehicleStatus VALUES('v006', 'Honda Navi 110 CC', 'Scooter', 1000, 'c006' ,'b006', 'available');
INSERT INTO VehicleStatus VALUES('v007', 'Ladies Cycle', 'Bicycle', 800, 'C007' ,'b007', 'available');
INSERT INTO VehicleStatus VALUES('v008', 'Mountain Cycle', 'Bicycle', 800, 'C008' ,'b008', 'available');
INSERT INTO VehicleStatus VALUES('v009', 'Bajaj Qute', 'Car', 4000, 'C009','b009', 'available');
INSERT INTO VehicleStatus VALUES('v010', 'Tuk Tuk 300 CC', 'Three Wheel', 3500, 'C010' ,'b010', 'available');

INSERT INTO BookingDetails VALUES('v001','b001');
INSERT INTO BookingDetails VALUES('v002','b002');
INSERT INTO BookingDetails VALUES('v003','b003');
INSERT INTO BookingDetails VALUES('v004','b004');
INSERT INTO BookingDetails VALUES('v005','b005');
INSERT INTO BookingDetails VALUES('v006','b006');
INSERT INTO BookingDetails VALUES('v007','b007');
INSERT INTO BookingDetails VALUES('v008','b008');
INSERT INTO BookingDetails VALUES('v009','b009');
INSERT INTO BookingDetails VALUES('v010','b010');

INSERT INTO User VALUES('gayan', 'letmeIn');
INSERT INTO User VALUES('hello','1234');
INSERT INTO User VALUES('niko','bye');
INSERT INTO User VALUES('1','1');

INSERT INTO Employee VALUES('E001','receptionist','nimal Perera' , '946101273V', 'No. 123, Galle Road, Colombo', 0112345678, 'chathura@gmail.com');
INSERT INTO Employee VALUES('E002', 'mechanic' ,'gayan Silva' , '947691234V', 'No. 456, Kandy Road, Kadawatha', 0113456789, 'nimasha@yahoo.com');

INSERT INTO Salaries VALUES('S001','Monthly Salary', 75000.00, 'Basic', 'January' , 'ep001');
INSERT INTO Salaries VALUES('S002','Monthly Salary', 65000.00, 'Basic', 'January' , 'ep002');

INSERT INTO Attendance VALUES('A001','2023-03-01', 'workday', '08:30:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A002','2023-03-01', 'workday', '08:30:00', '17:00:00', 'ep002');
INSERT INTO Attendance VALUES('A003','2023-03-02', 'workday', '08:45:00', '17:15:00', 'ep001');
INSERT INTO Attendance VALUES('A004','2023-03-02', 'workday', '08:45:00', '17:15:00', 'ep002');
INSERT INTO Attendance VALUES('A005','2023-03-03', 'workday', '08:15:00', '17:30:00', 'ep001');
INSERT INTO Attendance VALUES('A006','2023-03-03', 'workday', '08:15:00', '17:30:00', 'ep002');
INSERT INTO Attendance VALUES('A007','2023-03-04', 'workday', '08:00:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A008','2023-03-04', 'workday', '08:00:00', '17:00:00', 'ep002');
INSERT INTO Attendance VALUES('A009','2023-03-05', 'workday', '08:30:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A010','2023-03-05', 'workday', '08:30:00', '17:00:00', 'ep002');
INSERT INTO Attendance VALUES('A011','2023-03-06', 'holiday', NULL, NULL, 'ep001');
INSERT INTO Attendance VALUES('A012','2023-03-06', 'holiday', NULL, NULL, 'ep002');
INSERT INTO Attendance VALUES('A013','2023-03-07', 'workday', '08:15:00', '17:30:00', 'ep001');
INSERT INTO Attendance VALUES('A014','2023-03-07', 'workday', '08:15:00', '17:30:00', 'ep002');
INSERT INTO Attendance VALUES('A015','2023-03-08', 'workday', '09:00:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A016','2023-03-08', 'workday', '09:00:00', '17:00:00', 'ep002');
INSERT INTO Attendance VALUES('A017','2023-03-09', 'workday', '08:30:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A018','2023-03-09', 'workday', '08:30:00', '17:00:00', 'ep002');
INSERT INTO Attendance VALUES('A019','2023-04-27', 'workday', '08:00:00', '17:00:00', 'ep001');
INSERT INTO Attendance VALUES('A020','2023-04-27', 'workday', '08:00:00', '17:00:00', 'ep002');






