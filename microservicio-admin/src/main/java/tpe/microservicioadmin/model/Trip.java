package tpe.microservicioadmin.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Trip {
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
