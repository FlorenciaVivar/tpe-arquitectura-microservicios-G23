package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-trip", url = "http://localhost:8002")
public interface TripFeignClient {

    @GetMapping("/totalInvoiced")
    Integer getTotalInvoicedByDate(@RequestParam("year") Integer year,
                                   @RequestParam("month") Integer month);
}
