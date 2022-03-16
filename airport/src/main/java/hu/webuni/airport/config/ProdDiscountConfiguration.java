package hu.webuni.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.airport.service.DiscountService;
import hu.webuni.airport.service.SpecialDiscountService;

@Configuration // Konfigurációs osztály
@Profile("prod")
public class ProdDiscountConfiguration {
	@Bean // Legyártja a szervizt
	public DiscountService discountService() {
		return new SpecialDiscountService();
	}
}
