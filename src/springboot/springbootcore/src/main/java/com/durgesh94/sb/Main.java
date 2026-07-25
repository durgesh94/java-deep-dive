package com.durgesh94.sb;

import com.durgesh94.sb.order.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
       SpringApplication.run(Main.class, args);
        System.out.println("Spring Boot Started....!");
    }
}