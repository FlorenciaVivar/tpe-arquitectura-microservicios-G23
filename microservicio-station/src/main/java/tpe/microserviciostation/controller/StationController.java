package tpe.microserviciostation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microserviciostation.entities.StationEntity;
import tpe.microserviciostation.service.StationService;

import java.util.List;

@RestController
@RequestMapping("stations")
public class StationController {
    @Autowired
    StationService stationService;

    @GetMapping("")
    public ResponseEntity<List<StationEntity>> getAllStations(){
        List<StationEntity> stations = stationService.getAll();
        if (stations.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stations);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StationEntity> getStationById(@PathVariable("id") Long id){
        StationEntity station = stationService.findById(id);
        if (station == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(station);
    }
    @PostMapping("/addStation")
    public ResponseEntity<StationEntity> addStation(@RequestBody StationEntity station) {
        StationEntity newStation = stationService.addStation(station);
        return ResponseEntity.ok(newStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        StationEntity station = stationService.findById(id);
        if (station == null){
            return ResponseEntity.notFound().build();
        }
        stationService.delete(station);
        return ResponseEntity.noContent().build();
    }
}
