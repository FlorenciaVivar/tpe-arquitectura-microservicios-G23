package tpe.microservicioscooter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioscooter.dto.ScooterKilometerDTO;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;
import tpe.microservicioscooter.entities.ScooterEntity;
import tpe.microservicioscooter.service.ScooterService;
import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

   @Autowired
   ScooterService scooterService;

    @GetMapping("")
    public ResponseEntity<List<ScooterEntity>> getAllScooters(){

        List<ScooterEntity> scooters = scooterService.getAll();
        if (scooters.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterEntity> getScooterById(@PathVariable("id") Long id){
        ScooterEntity scooter = scooterService.findById(id);
        if (scooter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }

    //obtener todos los monopatines disponibles si esta vacio retorna 204 no content
    @GetMapping("/available")
    public ResponseEntity<List<ScooterEntity>> getAvailableScooters() {
        List<ScooterEntity> scooters = scooterService.getAvailableScooters();
        if (scooters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }
    //Quitar monopatín
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable Long id) {
        scooterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addScooter")
    public ResponseEntity<ScooterEntity> save(@RequestBody ScooterEntity scooter) {
        ScooterEntity savedScooter = scooterService.save(scooter);
        return ResponseEntity.ok(savedScooter);
    }

    //Registrar monopatín en mantenimiento respuesta 200.ok , si no existe monopatin respuesta 404 notfound
    @PutMapping("/maintenance/{id}")
    public ResponseEntity<ScooterEntity> registerMaintenance(@PathVariable Long id) {
        ScooterEntity scooter = scooterService.setScooterInMaintenance(id);
        if (scooter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }
    //Registrar fin de mantenimiento de monopatín. Respuesta 200.ok , si no existe monopatin respuesta 404 notfound
    @PutMapping("/finishMaintenance/{id}")
    public ResponseEntity<ScooterEntity> finishMaintenance(@PathVariable Long id) {
        ScooterEntity scooter = scooterService.finishMaintenance(id);
        if (scooter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }
    //Ubicar monopatínes en parada (opcional). Respuesta: 200.ok , si no existe: 204 No Content.
    @GetMapping("/stop/{scooterStop}")
    public ResponseEntity<List<ScooterEntity>> getScootersAtStop(@PathVariable String scooterStop) {
        List<ScooterEntity> scooters = scooterService.getScootersByStop(scooterStop);
        if (scooters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }

    //Generar reporte de uso de monopatines por kilómetros
    @GetMapping("/report/kilometers")
    public ResponseEntity<List<ScooterKilometerDTO>> getScooterKilometerReport() {
        List<ScooterKilometerDTO> scooterKilometerDTOList = scooterService.calculateTotalKilometersForScooter();
        return ResponseEntity.ok(scooterKilometerDTOList);
    }

    //Consultar cantidad de monopatines disponibles vs en mantenimiento
    @GetMapping("/quantity")
    public ResponseEntity<ScooterQuantityDTO> getScootersQuantity() {
        ScooterQuantityDTO scootersQuantity = scooterService.calculateQuantityScooter();
        return ResponseEntity.ok(scootersQuantity);
    }
}
