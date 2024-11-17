package tpe.microservicioadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class MicroservicioAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAdminApplication.class, args);
	}

}
