package com.westerostax.westerostax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.westerostax.westerostax.models", "com.westerostax.westerostax.repository",
		"com.westerostax.westerostax.services", "com.westerostax.westerostax.controllers" })
public class WesterostaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesterostaxApplication.class, args);
	}

}
