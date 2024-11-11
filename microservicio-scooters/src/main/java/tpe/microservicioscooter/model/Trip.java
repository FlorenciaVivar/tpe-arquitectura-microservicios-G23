package tpe.microservicioscooter.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class Trip {
    private Long id;
    private Long scooterId;
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
