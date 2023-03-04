package com.user.userops;


import com.user.userops.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UseropsApplication {

	//https://www.tutorialspoint.com/spring/index.htm
	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(UseropsApplication.class, args);

		//User bean = run.getBean(User.class);
	}

}
