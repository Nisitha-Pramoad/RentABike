package lk.ijse.rentabike.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleStatus {
        private String vehicleId;
        private String name;
        private String type;
        private double rentPrice;
        private String customerId;
        private String bookingdId;
        private String available;

        public VehicleStatus(String vehicleid, String vehiclename, String vehicetype, double rentprice, String customerid, String available) {
        }
}
