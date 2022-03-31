package hu.webuni.airport.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

//import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
	
	@InjectMocks   //Mockito do the innection, not the spring
	PriceService priceService;
	
	@Mock
	DiscountService discountService;
	
	@Test
	void testGetFinalPrice()throws Exception {
		int newPrice = new PriceService(p -> 5).getFinalPrice(100);
		//assertEquals(95, newPrice); //Junit teszt
		assertThat(newPrice).isEqualTo(95); // assertj library
	}
	
	@Test
	void testGetFinalPrice2()throws Exception {
		
		Mockito.when(discountService.getDiscountPercent(100)).thenReturn(5);
		
		int newPrice = priceService.getFinalPrice(100);
		assertThat(newPrice).isEqualTo(95); // assertj library
	}

}
