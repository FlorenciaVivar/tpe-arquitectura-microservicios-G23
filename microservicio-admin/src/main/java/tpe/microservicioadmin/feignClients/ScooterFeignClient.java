package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioadmin.model.Scooter;

@FeignClient(name = "microservicio-scooter", url = "http://localhost:8001")
public interface ScooterFeignClient {

    @PostMapping("/scooter/addScooter")
    ResponseEntity<Scooter> save(@RequestBody Scooter scooter);

    @DeleteMapping("/scooter/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/scooter/maintenance/{id}")
    ResponseEntity<Scooter> registerMaintenance(@PathVariable("id") Long id);

    @PutMapping("scooter/finishMaintenance/{id}")
    ResponseEntity<Scooter> finishMaintenance(@PathVariable("id")  Long id);
}
