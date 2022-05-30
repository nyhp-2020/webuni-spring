package hu.webuni.transport.nyhp.service;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.transport.nyhp.config.TransportConfigProperties;

public class IncomeDiscountService implements DiscountService {

	@Autowired
	TransportConfigProperties config;
	
	@Override
	public int getDiscountPercent(long income) {
		// TODO Auto-generated method stub
		return 0;
	}

}
