package tpe.microservicioscooter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioscooter.entities.ScooterEntity;
import tpe.microservicioscooter.service.ScooterService;

import java.util.List;

@RestController
@RequestMapping("/scooter")
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

    //Agregar monopatín
    @PostMapping("")
    public ResponseEntity<ScooterEntity> save(@RequestBody ScooterEntity scooter){
        ScooterEntity savedScooter = scooterService.save(scooter);
        return ResponseEntity.ok(savedScooter);
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
    //ESTO LO DEBE HACER SOLO EL ADMIN NO EL PROPIO MONOPATIN, se tiene q conectar con el microservicio admin con feignclient
    //Registrar monopatín en mantenimiento respuesta 200.ok , si no existe monopatin respuesta 404 notfound
    @PutMapping("/{id}/maintenance")
    public ResponseEntity<ScooterEntity> registerMaintenance(@PathVariable Long id) {
        ScooterEntity scooter = scooterService.setScooterInMaintenance(id);
        if (scooter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }
    //ESTO  LO TIENE Q HACER EL ADMIN
    //Registrar fin de mantenimiento de monopatín. Respuesta 200.ok , si no existe monopatin respuesta 404 notfound
//    @PutMapping("/{id}/finishMaintenance")
//    public ResponseEntity<ScooterEntity> finishMaintenance(@PathVariable Long id) {
//        ScooterEntity scooter = scooterService.finishMaintenance(id);
//        if (scooter == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(scooter);
//    }
    //Ubicar monopatínes en parada (opcional). Respuesta: 200.ok , si no existe: 204 No Content.
    @GetMapping("/stop/{scooterStop}")
    public ResponseEntity<List<ScooterEntity>> getScootersAtStop(@PathVariable String scooterStop) {
        List<ScooterEntity> scooters = scooterService.getScootersByStop(scooterStop);
        if (scooters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }

//    @GetMapping("/report/scooter-kilometers")
//    public ResponseEntity<List<Map<String, Object>>> getScooterKilometers() {
//        List<Map<String, Object>> scooterKilometers = scooterService.getScooterKilometers();
//        return ResponseEntity.ok(scooterKilometers);
//    }
}
