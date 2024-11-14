package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioadmin.model.Scooter;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;

import java.util.List;
//Segun los profesores sacar la url hace que ande el GateAway
//url comentada  url = "http://localhost:8001"
@FeignClient(name = "microservicio-scooter")
public interface ScooterFeignClient {

    @PostMapping("/scooters/addScooter")
    ResponseEntity<Scooter> save(@RequestBody Scooter scooter);

    @DeleteMapping("/scooters/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/scooters/maintenance/{id}")
    ResponseEntity<Scooter> registerMaintenance(@PathVariable("id") Long id);

    @PutMapping("scooters/finishMaintenance/{id}")
    ResponseEntity<Scooter> finishMaintenance(@PathVariable("id")  Long id);

    @GetMapping("/scooters/scooterByTrip/{year}")
    List<Scooter> getScootersByYear(@PathVariable("tripsQuantity") Integer tripsQuantity, @PathVariable("year") Integer year);

    @GetMapping("/scooters/quantity")
    ScooterQuantityDTO getQuantityScooter();

    @PostMapping("/scooters/byIds")
    List<Scooter> getScootersByIds(@RequestBody List<Long> scooterIds);
}
