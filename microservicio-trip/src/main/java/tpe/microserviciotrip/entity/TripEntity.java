package tpe.microserviciotrip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long scooterId;
    private Integer year;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double distanceTraveled;
    private double tripPrice;
    @Enumerated(EnumType.STRING)
    private TripStatus statusTrip;
    private Double amount;

    public enum TripStatus {
        IN_PROGRESS,
        COMPLETED,
        PAUSED
    }

}