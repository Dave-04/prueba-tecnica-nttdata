package com.microservice.usuariointerno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceUsuarioInternoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUsuarioInternoApplication.class, args);
    }

}
