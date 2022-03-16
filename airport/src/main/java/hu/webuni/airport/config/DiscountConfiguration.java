package hu.webuni.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.airport.service.DefaultDiscountService;
import hu.webuni.airport.service.DiscountService;

@Configuration // Konfigurációs osztály
@Profile("!prod")
public class DiscountConfiguration {
	@Bean // Legyártja a szervizt
	public DiscountService discountService() {
		return new DefaultDiscountService();
	}
}
