package com.durgesh94.demo;

import com.durgesh94.demo.payment.PaymentGateway;
import com.durgesh94.demo.payment.PaymentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
	   SpringApplication.run(DemoApplication.class, args);
    }

}
