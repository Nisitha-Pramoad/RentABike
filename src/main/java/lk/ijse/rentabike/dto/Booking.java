package lk.ijse.rentabike.dto;


import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Booking {
    private String bookingId;
    private String chooseBike;
    private String pickUpLocation;
    private Date pickUpDate;
    private String pickUpTime;
    private String dropOffLocation;
    private Date dropOffDate;
    private String dropOffTime;
    private String bookingStatus;



}
