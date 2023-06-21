package com.westerostax.westerostax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.westerostax.westerostax" })
public class WesterostaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesterostaxApplication.class, args);
	}

}
