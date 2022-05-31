package hu.webuni.transport.nyhp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.nyhp.config.TransportConfigProperties;

@Service
public class IncomeDiscountService implements DiscountService {

	@Autowired
	TransportConfigProperties config;

	@Override
	public int getDiscountPercent(long delay) {
		if (delay > config.getMinute().getLimit3())
			return config.getDiscount().getPercent3();
		
		if (delay > config.getMinute().getLimit2())
			return config.getDiscount().getPercent2();
		
		if (delay > config.getMinute().getLimit1())
			return config.getDiscount().getPercent2();
		
		return 0;
	}

}
