package hu.webuni.hr.nyhp.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.repository.HolidayRepository;

@Service
public class HolidayService {
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Transactional
	public Holiday createHoliday(long clid, LocalDate start, LocalDate end) {
		Employee employee = employeeService.findById(clid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(end.isBefore(start))
			throw new EndDateEarlierThanStartDateException();
		Holiday holiday = new Holiday();
		holiday.setClaimer(employee);
		holiday.setStart(start);
		holiday.setEnd(end);
		holiday.setClaimDate(LocalDate.now());
		return holidayRepository.save(holiday);
	}

}
