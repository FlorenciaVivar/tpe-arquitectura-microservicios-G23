package tpe.microservicioscooter.dto;

public class ScooterKilometerDTO {
    private String scooterId;
    private double kilometers;

    public ScooterKilometerDTO(String scooterId, double kilometers) {
        this.scooterId = scooterId;
        this.kilometers = kilometers;
    }

    // Getters y setters
    public String getScooterId() {
        return scooterId;
    }

    public void setScooterId(String scooterId) {
        this.scooterId = scooterId;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }
}
