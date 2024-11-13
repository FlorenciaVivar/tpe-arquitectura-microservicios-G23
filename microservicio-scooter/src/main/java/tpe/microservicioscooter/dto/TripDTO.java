package tpe.microservicioscooter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripDTO {
    private Long id;
    private double distanceTraveled;
    private TripStatus statusTrip;

    public TripDTO(Long id, double distanceTraveled) {
        this.id = id;
        this.distanceTraveled = distanceTraveled;
    }
    public enum TripStatus {
        IN_PROGRESS,
        COMPLETED,
        PAUSED
    }
}
