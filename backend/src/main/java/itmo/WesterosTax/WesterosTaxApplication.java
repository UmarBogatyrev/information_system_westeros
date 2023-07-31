package itmo.WesterosTax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@EnableJpaRepositories("itmo.WesterosTax.entity")
@ComponentScan(basePackages = { "itmo.WesterosTax.entity" })
@EntityScan("itmo.WesterosTax.entity")
public class WesterosTaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesterosTaxApplication.class, args);
	}

}
