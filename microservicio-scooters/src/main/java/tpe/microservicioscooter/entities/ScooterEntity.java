package tpe.microservicioscooter.entities;

import jakarta.persistence.*;
import lombok.*;
import tpe.microservicioadmin.entity.AdminEntity;

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

}
