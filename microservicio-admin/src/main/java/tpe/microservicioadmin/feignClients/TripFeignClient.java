package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tpe.microservicioadmin.dto.ReportTripDTO;
import tpe.microservicioadmin.model.Trip;

import java.util.List;
//Segun los profesores sacar la url hace que ande el GateAway
//url comentada  url = "http://localhost:8002"
@FeignClient(name = "microservicio-trip")
public interface TripFeignClient {

    @GetMapping("/trips/totalInvoiced")
    Integer getTotalInvoicedByDate(@RequestParam Integer year, @RequestParam Integer month1, @RequestParam Integer month2);

    @GetMapping("/trips/reportTripsByScooter")
    List<ReportTripDTO> getReportTripsByScooter();

    @GetMapping("/trips/scootersWithMinTrips")
    List<Long> getScooterIdsWithMinTripsInYear(@RequestParam int year, @RequestParam int minTrips);
}
