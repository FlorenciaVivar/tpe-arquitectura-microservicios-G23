package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicio-scooter", url = "http://localhost:8001")
public interface ScooterFeignClient {
}
