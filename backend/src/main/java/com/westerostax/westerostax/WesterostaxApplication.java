package com.westerostax.westerostax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.westerostax.westerostax.entity")
@ComponentScan(basePackages = { "com.westerostax.westerostax.entity" })
@EntityScan("com.westerostax.westerostax.entity")
public class WesterostaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesterostaxApplication.class, args);
	}

}
