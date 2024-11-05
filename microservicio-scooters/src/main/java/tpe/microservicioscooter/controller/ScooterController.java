package tpe.microservicioscooter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioscooter.entities.scooterEntity;

import java.util.List;

@RestController
@RequestMapping("/scooters")
public class scooterController {
    tpe.microservicioscooter.service.scooterService scooterService;

    @GetMapping("/")
    public ResponseEntity<List<scooterEntity>> getAllScooters(){
        List<scooterEntity> scooters = scooterService.getAll();
        if (scooters.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<scooterEntity> getScooterById(@PathVariable("id") Long id){
        scooterEntity scooter = scooterService.findById(id);
        if (scooter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }

    @PostMapping("")
    public ResponseEntity<scooterEntity> save(@RequestBody scooterEntity scooter){
        scooterEntity savedScooter = scooterService.save(scooter);
        return ResponseEntity.ok(savedScooter);
    }

}
