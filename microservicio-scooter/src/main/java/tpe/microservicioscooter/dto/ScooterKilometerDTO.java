package tpe.microservicioscooter.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScooterKilometerDTO {
    // Getters y setters
    private String scooterId;
    private double kilometers;

    public ScooterKilometerDTO(String scooterId, double kilometers) {
        this.scooterId = scooterId;
        this.kilometers = kilometers;
    }

}
