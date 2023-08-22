package lk.ijse.rentabike.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BookingTm {
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
