package pl.uncleglass.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;
import pl.uncleglass.api.domain.Condition;
import pl.uncleglass.api.domain.car.Car;
import pl.uncleglass.api.domain.car.CarRepository;
import pl.uncleglass.api.domain.manufacturer.Manufacturer;
import pl.uncleglass.api.domain.manufacturer.ManufacturerRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(ManufacturerRepository repository, CarRepository carRepository) {
		return args -> {
			Manufacturer audi = repository.save(new Manufacturer(100, "Audi"));
			Manufacturer chevrolet = repository.save(new Manufacturer(101, "Chevrolet"));
			Manufacturer ford = repository.save(new Manufacturer(102, "Ford"));
			Manufacturer bmw = repository.save(new Manufacturer(103, "BMW"));
			Manufacturer dodge = repository.save(new Manufacturer(104, "Dodge"));

			Car car = new Car();
			car.setCondition(Condition.NEW);
			car.getDetails().setManufacturer(audi);
			car.getDetails().setBody("Sedan");
			car.getDetails().setModel("Trela");
			carRepository.save(car);
			Car car1 = new Car();
			car1.setCondition(Condition.USED);
			car1.getDetails().setManufacturer(chevrolet);
			car1.getDetails().setBody("Hatchback");
			car1.getDetails().setModel("Hunday");
			carRepository.save(car1);
			Car car2 = new Car();
			car2.setCondition(Condition.NEW);
			car2.getDetails().setManufacturer(ford);
			car2.getDetails().setBody("Coupe");
			car2.getDetails().setModel("Merc");
			carRepository.save(car2);
			Car car3 = new Car();
			car3.setCondition(Condition.USED);
			car3.getDetails().setManufacturer(bmw);
			car3.getDetails().setBody("Micro");
			car3.getDetails().setModel("Mini");
			carRepository.save(car3);
			Car car4 = new Car();
			car4.setCondition(Condition.NEW);
			car4.getDetails().setManufacturer(dodge);
			car4.getDetails().setBody("Suv");
			car4.getDetails().setModel("ModelSuv");
			carRepository.save(car4);
		};
	}

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
//
//	/**
//	 * Web Client for the maps (location) API
//	 * @param endpoint where to communicate for the maps API
//	 * @return created maps endpoint
//	 */
//	@Bean(name="maps")
//	public WebClient webClientMaps(@Value("${maps.endpoint}") String endpoint) {
//		return WebClient.create(endpoint);
//	}
//
//	/**
//	 * Web Client for the pricing API
//	 * @param endpoint where to communicate for the pricing API
//	 * @return created pricing endpoint
//	 */
//	@Bean(name="pricing")
//	public WebClient webClientPricing(@Value("${pricing.endpoint}") String endpoint) {
//		return WebClient.create(endpoint);
//	}

	@Bean(name = "client")
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
}
