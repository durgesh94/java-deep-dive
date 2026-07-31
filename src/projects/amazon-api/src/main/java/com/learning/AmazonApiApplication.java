package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AmazonApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmazonApiApplication.class, args);

        System.out.println("Amazon API started successfully........!");
    }
}