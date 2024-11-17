package tpe.microserviciouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioUserApplication.class, args);
    }
}