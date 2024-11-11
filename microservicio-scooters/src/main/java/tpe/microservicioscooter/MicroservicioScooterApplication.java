package tpe.microservicioscooter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroservicioScooterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioScooterApplication.class, args);
	}

}
