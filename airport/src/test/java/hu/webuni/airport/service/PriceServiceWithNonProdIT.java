package hu.webuni.airport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest //Integration test
@ActiveProfiles("test") //test profile switched on
public class PriceServiceWithNonProdIT {
	
	@Autowired
	PriceService priceService;
	
	@Test
	void testGetFinalPriceWithHighPrice()throws Exception {
			
		int newPrice = priceService.getFinalPrice(11000);
		assertThat(newPrice).isEqualTo(9900); // assertj library
	}

}
