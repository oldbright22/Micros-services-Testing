package com.motor.carops;

import com.motor.carops.model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CaropsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(CaropsApplication.class, args);

		//Car bean = run.getBean(Car.class);

	}

}
