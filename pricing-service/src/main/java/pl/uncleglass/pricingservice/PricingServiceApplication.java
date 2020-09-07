package pl.uncleglass.pricingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import pl.uncleglass.pricingservice.domain.Price;
import pl.uncleglass.pricingservice.domain.PriceRepository;
import pl.uncleglass.pricingservice.service.PricingService;

import java.util.Collection;

@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PriceRepository repository) {
		return args -> {
			Collection<Price> prices = PricingService.getPrices();
			repository.saveAll(prices);
		};
	}
}
//TODO Convert the Pricing Service to a microservice, registered on a Eureka server. Also, add an additional test for the microservice.