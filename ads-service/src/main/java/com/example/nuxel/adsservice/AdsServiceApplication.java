package com.example.nuxel.adsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableEurekaClient
public class AdsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdsServiceApplication.class, args);
    }

}
