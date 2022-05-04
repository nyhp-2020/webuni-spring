package hu.webuni.hr.nyhp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.repository.HolidayRepository;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	@Autowired
	HolidayRepository holidayRepository;
}
