package hu.webuni.transport.nyhp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
	
	@Autowired
	IncomeDiscountService idService;
	
	public long getNewIncome(long income,long delay) {
		return  (long) (income*(100-idService.getDiscountPercent(delay))/100.0);
	}

}
