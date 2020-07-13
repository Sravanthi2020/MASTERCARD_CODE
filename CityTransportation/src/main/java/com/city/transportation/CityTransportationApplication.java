package com.city.transportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages  ="com.city.transportation")
public class CityTransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityTransportationApplication.class, args);
	}

}
