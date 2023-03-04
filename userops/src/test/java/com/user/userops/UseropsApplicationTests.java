package com.user.userops;

import com.user.userops.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UseropsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser(){
		User user = new User(1,"Timmy", LocalDate.now());
		System.out.println(user);

	}
}
