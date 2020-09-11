package pl.uncleglass.pricingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.uncleglass.pricingservice.domain.Price;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void getPrice() {
		ResponseEntity<Price> response = restTemplate.getForEntity("http://localhost:" + port + "/prices/1", Price.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().getCurrency(), equalTo("USD"));
	}
}
