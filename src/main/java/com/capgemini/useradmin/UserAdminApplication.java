package com.capgemini.useradmin;

import com.capgemini.useradmin.util.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UserAdminApplication {


	@Autowired
	private Seeder seeder;

	public static void main(String[] args) {
		SpringApplication.run(UserAdminApplication.class, args);
	}

	@PostConstruct
	public void seedDatabase() {
		seeder.seedUsers();
	}
}