package tpe.microservicioadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scooter  {
        private Long id;
        private String scooterStop;
        private String status;
        private boolean available;
        private String gps;
        private int timeInUse;
        private int totalKilometers;



    }