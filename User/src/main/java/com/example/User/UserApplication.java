package com.example.User;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Customer Service API",
				version = "1.0",
				description = "Documentation Customer Service API v1.0",
				contact = @Contact(
						name = "Lasal Hettiarachchi",
						email = "lasal@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
