package com.example.gateways;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySApplication.class, args);
    }

}
