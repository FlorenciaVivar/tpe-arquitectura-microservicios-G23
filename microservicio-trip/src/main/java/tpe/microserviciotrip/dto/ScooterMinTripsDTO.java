package tpe.microserviciotrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScooterMinTripsDTO {

        private Long scooterId;
        private Long tripCount;

        public ScooterMinTripsDTO(Long scooterId, Long tripCount) {
        this.scooterId = scooterId;
        this.tripCount = tripCount;
    }
}

