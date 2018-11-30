package ca.concordia.soen6841.position;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PositionSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionSpringBootApplication.class, args);
	}
}