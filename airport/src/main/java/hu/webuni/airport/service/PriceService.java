package hu.webuni.airport.service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

	// @Autowired //Tagváltozó injektálás
	private DiscountService discountservice;

	public PriceService(DiscountService discountService) {
		// super(); Ha volna ősosztályunk is...
		this.discountservice = discountService;
	}

	public int getFinalPrice(int price) {
		return (int) (price / 100.0 * (100 - discountservice.getDiscountPercent(price)));
	}
}
