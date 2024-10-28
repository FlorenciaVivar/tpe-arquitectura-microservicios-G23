package tpe.microserviciotrip;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float distanciaRecorrida;
    private LocalDate fechaViaje;
    private LocalTime horaFinalizacion;
    //entidad parada para obtener el fin del viaje ??
    private Parada inicio;
    private Parada fin;


}