package com.mj.guest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationGuest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGuest.class, args);
    }
}
