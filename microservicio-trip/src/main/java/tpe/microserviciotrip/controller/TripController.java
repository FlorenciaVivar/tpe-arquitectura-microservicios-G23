package tpe.microserviciotrip.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microserviciotrip.entity.TripEntity;
import tpe.microserviciotrip.service.TripService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/trips")
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping("")
    public ResponseEntity<List<TripEntity>> getAllTrips() {
        List<TripEntity> t = tripService.getAll();
        if (t.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripEntity> getTripById(@PathVariable("id") Long id) {
        TripEntity t = tripService.findById(id);
        if (t == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @PostMapping("")
    public ResponseEntity<TripEntity> save(@RequestBody TripEntity trip) {
        TripEntity saveTrip = tripService.save(trip);
        return ResponseEntity.ok(saveTrip);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripById(@PathVariable("id") Long id) {
        try {
            if (tripService.existsById(id)) {  // Comprobar si el viaje existe antes de eliminarlo
                tripService.deleteById(id);
                log.info("Trip con ID " + id + " eliminado exitosamente.");
                return ResponseEntity.noContent().build();  // Retorna 204 No Content si se elimina correctamente
            } else {
                log.warn("Trip con ID " + id + " no encontrado para eliminar.");
                return ResponseEntity.notFound().build();  // Retorna 404 Not Found si no se encuentra el viaje
            }
        } catch (Exception e) {
            log.error("Error al eliminar el Trip con ID " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // Retorna 500 en caso de error
        }
    }


    @GetMapping("/totalInvoiced")
    public ResponseEntity<?> getTotalInvoicedByDate(@RequestParam("year") Integer year,
                                                      @RequestParam("month1") Integer month1,
                                                      @RequestParam("month2") Integer month2) {
        try {
            log.info("year: " + year + " month1: " + month1 + " month2: " + month2);
            Integer total = tripService.calculateTotalInvoiced(year, month1,month2);
            log.info("Total: " + total);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }



    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Servidor en funcionamiento");
    }

















}