package tpe.microservicioscooter.feignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tpe.microservicioscooter.dto.TripDTO;

import java.util.List;

@FeignClient(name = "microservicio-trip", url = "http://localhost:8002")
public interface TripFeignClient {

    @GetMapping("/trips")
    List<TripDTO> getAllTrips();

    @GetMapping("/trips/scooter/{id}")
    List<TripDTO> getTripsByScooterId(@PathVariable("id") Long id);
}

