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
@Table(name = "trips")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double distanceTraveled;
    @Enumerated(EnumType.STRING)
    private TripStatus statusTrip;

    public enum TripStatus {
        IN_PROGRESS,
        COMPLETED,
        PAUSED
    }

}