package com.durgesh94.crudDemoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class CrudDemoAppApplication {

	public static void main(String[] args) {

        SpringApplication.run(CrudDemoAppApplication.class, args);

        System.out.println("CRUD App Started...!");
	}

}
