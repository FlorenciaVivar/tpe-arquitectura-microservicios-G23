package tpe.microservicioadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportTripDTO {
    private Long scooterId;
    private Double totalDistance;


}

