package hu.webuni.hr.nyhp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.repository.HolidayRepository;

@Service
public class HolidayService {
	
	@Autowired
	HolidayRepository holidayRepository;

}
