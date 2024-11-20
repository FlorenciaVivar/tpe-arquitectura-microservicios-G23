package tpe.microservicioscooter.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScooterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scooterStop;
    private String status;
    private boolean available;
    private String gps;
    private Integer timeInUse;
    private double totalKilometers;

    public ScooterEntity(long l, String available, boolean b, double v, String stop1, int i) {
    }
}
