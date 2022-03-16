package hu.webuni.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import hu.webuni.airport.service.DefaultDiscountService;
import hu.webuni.airport.service.DiscountService;
import hu.webuni.airport.service.PriceService;

@SpringBootApplication
public class AirportApplication implements CommandLineRunner {

	@Autowired // tagváltozó injektálás
	PriceService priceService;

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(priceService.getFinalPrice(200));
		System.out.println(priceService.getFinalPrice(20000));
	}

// Ezt kiszerveztük a konfigurációs osztályba (DiscountConfiguration)
//	@Bean //Legyártja a szervizt
//	public DiscountService discountService() {
//		return new DefaultDiscountService();
//	}

}
