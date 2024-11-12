package tpe.microservicioscooter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ScooterQuantityDTO {
    private Integer scootersAvailable;
    private Integer scootersMaintenance;

}
