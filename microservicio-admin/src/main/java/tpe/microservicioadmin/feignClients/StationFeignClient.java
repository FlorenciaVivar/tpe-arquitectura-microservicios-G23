package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tpe.microservicioadmin.model.Station;
//Segun los profesores sacar la url hace que ande el GateAway
//url comentada  url = "http://localhost:8005"
@FeignClient(name = "microservicio-station")
public interface StationFeignClient {

    @PostMapping("/stations/addStation")
    ResponseEntity<Station> save(@RequestBody Station station);

    @DeleteMapping("/stations/deleteStation/{id}")
    void delete(@PathVariable Long id);

}