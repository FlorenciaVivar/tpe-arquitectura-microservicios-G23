package tpe.microserviciotrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microserviciotrip.entity.TripEntity;
import tpe.microserviciotrip.service.TripService;

import java.util.List;

@RestController
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
}