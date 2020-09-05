package pl.uncleglass.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingServiceApplication.class, args);
	}

}
//TODO Convert the Pricing Service to a microservice, registered on a Eureka server. Also, add an additional test for the microservice.