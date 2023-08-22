package lk.ijse.rentabike.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleStatusTm {
    private String vehicleId;
    private String name;
    private String type;
    private double rentPrice;
    private String customerId;
    private String bookingdId;
    private String available;
}
